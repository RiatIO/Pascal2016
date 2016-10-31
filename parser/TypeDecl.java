package parser;

import main.*;
import scanner.*;
import java.util.ArrayList;
import static scanner.TokenKind.*;

class TypeDecl extends PascalDecl{

    types.Type type;

    TypeDecl(String id, int lNum){
        super(id,lNum);
    }

    @Override void check(Block curScope, Library lib) {
    }

    @Override public String identify() {
        return "<Param-decl> " + name + "on line " + lineNum;
    }

    static TypeDecl parse(Scanner s){
        enterParser("type decl");


        leaveParser("type decl");
        return null;
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
