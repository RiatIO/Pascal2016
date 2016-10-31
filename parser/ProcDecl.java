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

    @Override void check(Block curScope, Library lib) {
    }

    @Override public String identify() {
        return "<Proc-decl> " + name + "on line " + lineNum;
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
        // Main.log.prettyPrintLn();
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
        where.error("You cannot assign to a constant!");
    }
    @Override void checkWhetherFunction(PascalSyntax where){
        where.error("You do not have the func");
    }
    @Override void checkWhetherProcedure(PascalSyntax where){

    }

    @Override void checkWhetherValue(PascalSyntax where){
        where.error("No value"");
    }

}
