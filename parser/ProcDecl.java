package parser;

import main.*;
import scanner.*;
import java.util.ArrayList;
import static scanner.TokenKind.*;

class ProcDecl extends PascalDecl {
    ParamDeclList pdl;
    Block b;

    ProcDecl(String id, int lNum){
        super(id, lNum);
    }

    @Override void genCode(CodeFile f) {
        int size = b.vdp == null ? 32 : b.vdp.size;

        f.genInstr("proc$" + f.getLabel(name), "enter", String.format("$%d,$%d", size, b.blockId), "");
        b.genCode(f);
        f.genInstr("", "", "leave", "");
        f.genInstr("", "", "ret", "");
	}

	@Override void check(Block curScope, Library lib) {
        curScope.addDecl(name, this);

        if (pdl != null) {
            b.outerScope = curScope;
            b.blockId = (b.levelCount + 1); // Ugly but must be done.
            pdl.check(b, lib);
        }

        b.check(curScope, lib);
        declLevel = curScope.blockId;
    }

    @Override public String identify() {
        if (lineNum == 0)
            return "<proc decl> " + name + " in the library";

        return "<proc decl> " + name + " on line " + lineNum;
    }

    @Override public void prettyPrint() {
        Main.log.prettyPrintLn();

        Main.log.prettyPrint("procedure " + name);

        if (pdl != null) {
            Main.log.prettyPrint(" ");
            pdl.prettyPrint();
        }

        Main.log.prettyPrintLn(";");

        b.prettyPrint();

        Main.log.prettyPrintLn("; {" + name + "}");
    }

    static ProcDecl parse(Scanner s) {
        enterParser("proc decl");
        s.skip(procedureToken);
        s.test(nameToken);

        ProcDecl pd = new ProcDecl(s.curToken.id, s.curLineNum());
        s.readNextToken();

        if (s.curToken.kind == leftParToken) {
            pd.pdl = ParamDeclList.parse(s);
        }

        s.skip(semicolonToken);
        pd.b = Block.parse(s);
        s.skip(semicolonToken);

        leaveParser("proc decl");
        return pd;
    }

    @Override void checkWhetherAssignable(PascalSyntax where){
        where.error("ProcDecl - You cannot assign to a constant!");
    }
    @Override void checkWhetherFunction(PascalSyntax where){
        where.error("ProcDecl - You do not have the func");
    }
    @Override void checkWhetherProcedure(PascalSyntax where){

    }

    @Override void checkWhetherValue(PascalSyntax where){
        where.error("ProcDecl - No value");
    }

}
