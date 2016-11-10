package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

class AssignStatm extends Statement {
    Variable v;
    Expression e;

    AssignStatm(int lNum){
        super(lNum);
    }

	@Override void genCode(CodeFile f) {
        e.genCode(f);

        if (v.varDecl instanceof FuncDecl) {
            Main.debug(lineNum, "AssignStatm", "" + v.name);
            // v.varDecl.genCode(f);
            // v.genCode(f);

            f.genInstr("", "movl", "-"+ 4 * (v.varDecl.declLevel+1) +"(%ebp),%edx", "");
            f.genInstr("", "movl", "%eax,-"+ (v.varDecl.declOffset) +"(%edx)", " " + v.varDecl.name + " :=");


        } else if (v.expr != null) {
            // System.out.println(v.varDecl.declLevel);
        } else {
            f.genInstr("", "movl", "-100(%ebp),%edx", "");
            f.genInstr("", "movl", "%eax,-32(%edx)", " " + v.varDecl.name + " :=");
        }
	}

	@Override void check(Block curScope, Library lib) {
        v.check(curScope, lib);
        v.varDecl.checkWhetherAssignable(this);
        e.check(curScope, lib);

        v.type.checkType(e.type, ":=", this, "Different types in assignment");
    }

    @Override public String identify() {
        return "<Assign-statm> on line " + lineNum;
    }

    @Override public void prettyPrint() {
        v.prettyPrint();
        Main.log.prettyPrint(" := ");
        e.prettyPrint();
    }

    static AssignStatm parse(Scanner s){
        enterParser("assign statm");

        AssignStatm asgn = new AssignStatm(s.curLineNum());
        asgn.v = Variable.parse(s);
        s.skip(assignToken);
        asgn.e = Expression.parse(s);

        leaveParser("assign statm");
        return asgn;
    }
}
