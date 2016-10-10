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

    @Override public String identify() {
        return "<const-decl> " + name + " on line " + lineNum;
    }



    static ConstDecl parse(Scanner s) {
        enterParser("const-decl");
        s.test(nameToken);

        ConstDecl cd = new ConstDecl(s.curToken.id, s.curLineNum());
        s.readNextToken();
        s.skip(equalToken);
        //checkWhetherAssignable??
        cd.cst = Constant.parse(s); //cd.const.context = p;
        // s.readNextToken();
        s.skip(semicolonToken);

        leaveParser("const-decl");
        return p;
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
