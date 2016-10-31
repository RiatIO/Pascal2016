package parser;

import main.*;

import scanner.*;
import java.util.ArrayList;
import static scanner.TokenKind.*;

class FuncDecl extends ProcDecl {
    ParamDeclList pdl;
    Block b;
    TypeName tn;

    FuncDecl(String id, int lNum) {
        super(id, lNum);
    }

    @Override void check(Block curScope, Library lib) {
    }

    @Override public String identify() {
        return "<Func-decl> "+ name + "on line " + lineNum;
    }

    @Override public void prettyPrint() {
        Main.log.prettyPrintLn();
        Main.log.prettyPrint("function " + name);

        if (pdl != null) {
            Main.log.prettyPrint(" ");
            pdl.prettyPrint();
        }

        Main.log.prettyPrint(": ");
        tn.prettyPrint();

        Main.log.prettyPrintLn(";");

        b.prettyPrint();

        Main.log.prettyPrintLn("; {" + name + "}");
        // Main.log.prettyPrintLn();
    }

    static FuncDecl parse(Scanner s) {
        enterParser("func decl");
        s.skip(functionToken);
        s.test(nameToken);

        FuncDecl fd = new FuncDecl(s.curToken.id, s.curLineNum());

        s.readNextToken();

        if (s.curToken.kind != colonToken) {
            fd.pdl = ParamDeclList.parse(s);
        }

        s.skip(colonToken);
        fd.tn = TypeName.parse(s);
        s.skip(semicolonToken);

        fd.b = Block.parse(s);
        s.skip(semicolonToken);

        leaveParser("func decl");
        return fd;
    }

    @Override void checkWhetherAssignable(PascalSyntax where){
        where.error("Cannot assign a func");
    }
    @Override void checkWhetherFunction(PascalSyntax where){

    }
    @Override void checkWhetherProcedure(PascalSyntax where){
        where.error("Not a procedure");
    }
    @Override void checkWhetherValue(PascalSyntax where){
        where.error("Not a value");
    }
}
