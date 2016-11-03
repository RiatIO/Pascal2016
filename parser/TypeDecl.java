package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

class TypeDecl extends PascalDecl {

    PascalDecl ref;

    TypeDecl(String id, int lNum){
        super(id, lNum);
    }

    @Override void check(Block curScope, Library lib) {
        curScope.addDecl(name, this);
        System.out.println("TYPEDECL " + name);
    }

    @Override public String identify() {
        if (lineNum == 0)
            return "<type decl> " + name + " in the library";

        return "<type decl> " + name + " on line " + lineNum;

    }

    static TypeDecl parse(Scanner s){
        enterParser("type decl");

        s.test(nameToken);
        TypeDecl td = new TypeDecl(s.curToken.id, s.curLineNum());
        s.readNextToken();

        leaveParser("type decl");
        return td;
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
