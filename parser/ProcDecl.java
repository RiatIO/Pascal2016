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
        curScope.addDecl(name, this);
        if (pdl != null) {
            b.outerScope = curScope;
            pdl.check(b, lib);
        }

        b.check(curScope, lib);
    }

    @Override public String identify() {
        return "<proc decl> " + name + " in the library";
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
