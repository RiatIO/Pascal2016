package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

class IfStatm extends Statement {
    Expression expr;
    Statement preS, postS;

    types.Type type;

    IfStatm(int lNum){
        super(lNum);
    }

    //NOTE: Dry code this.
    @Override void genCode(CodeFile f) {
        expr.genCode(f);
        if (postS != null) {
            String lab1 = f.getLocalLabel(), lab2 = f.getLocalLabel();
            f.genInstr("", "cmpl", "$0,%eax", "");
            f.genInstr("", "je", lab1, "");
            preS.genCode(f);
            f.genInstr("", "jmp", lab2, "");
            f.genInstr(lab1, "", "", "");
            postS.genCode(f);
            f.genInstr(lab2, "", "", "");
        } else {
            String lab = f.getLocalLabel();
            f.genInstr("", "cmpl", "$0,%eax", "");
            f.genInstr("", "je", lab, "");
            preS.genCode(f);
            f.genInstr(lab, "", "", "");
        }
	}

	@Override void check(Block curScope, Library lib) {
        expr.check(curScope, lib);
        expr.type.checkType(lib.boolType, "if-test", this, "not valid");

        preS.check(curScope, lib);

        if (postS != null) {
            postS.check(curScope, lib);
        }
    }

    @Override public String identify() {
        return "<If-statm> on line " + lineNum;
    }

    @Override public void prettyPrint() {
        Main.log.prettyPrint("if ");
        expr.prettyPrint();
        Main.log.prettyPrintLn(" then");
        Main.log.prettyIndent();
        preS.prettyPrint();
        Main.log.prettyOutdent();
        if (postS != null) {
            Main.log.prettyPrintLn();
            Main.log.prettyPrintLn("else");
            Main.log.prettyIndent();
            postS.prettyPrint();
            Main.log.prettyOutdent();
        }
    }

    static IfStatm parse(Scanner s){
        enterParser("if-statm");

        IfStatm ifs = new IfStatm(s.curLineNum());
        s.skip(ifToken);

        ifs.expr = Expression.parse(s);
        s.skip(thenToken);

        ifs.preS = Statement.parse(s);

        if (s.curToken.kind == elseToken) {
            s.skip(elseToken);
            ifs.postS = Statement.parse(s);
        }

        leaveParser("if-statm");
        return ifs;
    }
}
