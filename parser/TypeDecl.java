package parser;

import main.*;
import scanner.*;
import java.util.ArrayList;
import static scanner.TokenKind.*;

class TypeDecl extends PascalDecl{

    TypeDecl(String id, int lNum){
        super(id,lNum);
    }

    @Override public String identify() {
        return "<Param-decl> " + name + "on line " + lineNum;
    }

    static TypeDecl parse(Scanner s){
        enterParser("TypeDecl");


        leaveParser("TypeDecl");
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
