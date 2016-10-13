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

    void checkWhetherAssignable(PascalSyntax where){

    }
    void checkWhetherFunction(PascalSyntax where){

    }
    void checkWhetherProcedure(PascalSyntax where){

    }
    void checkWhetherValue(PascalSyntax where){

    }

}
