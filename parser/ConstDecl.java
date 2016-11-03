package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

/* <program> ::= ’program’ <name> ’:’ <block> '.' */

class ConstDecl extends PascalDecl {
    Constant cst;

    ConstDecl(String id, int lNum) {
        super(id, lNum);
    }

    @Override void check(Block curScope, Library lib) {
        cst.check(curScope, lib);
        type = cst.type;
        curScope.addDecl(name, this);
    }

    @Override public String identify() {
        return "<const decl> " + name + " in the library";
    }

    @Override public void prettyPrint() {
        Main.log.prettyPrint(name + " = ");
        cst.prettyPrint();
        Main.log.prettyPrint(";");
    }

    static ConstDecl parse(Scanner s) {
        enterParser("const decl");

        s.test(nameToken);
        ConstDecl cd = new ConstDecl(s.curToken.id, s.curLineNum());
        s.readNextToken();

        s.skip(equalToken);
        cd.cst = Constant.parse(s);
        s.skip(semicolonToken);

        leaveParser("const decl");
        return cd;
    }

    @Override void checkWhetherAssignable(PascalSyntax where){

    }
    @Override void checkWhetherFunction(PascalSyntax where){
        where.error("Not a func");
    }
    void checkWhetherProcedure(PascalSyntax where){
        where.error("Not a proc");
    }
    void checkWhetherValue(PascalSyntax where){
        where.error("Not a value");
    }
}
