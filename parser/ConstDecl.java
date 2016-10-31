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
    }

    @Override public String identify() {
        return "<const decl> " + name + " on line " + lineNum;
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

    void checkWhetherAssignable(PascalSyntax where){

    }
    void checkWhetherFunction(PascalSyntax where){

    }
    void checkWhetherProcedure(PascalSyntax where){

    }
    void checkWhetherValue(PascalSyntax where){

    }
}
