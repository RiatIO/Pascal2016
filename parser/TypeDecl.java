package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

class TypeDecl extends PascalDecl {

    TypeDecl(String id, int lNum){
        super(id, lNum);
    }

    @Override void genCode(CodeFile f) {

	}

	@Override void check(Block curScope, Library lib) {
        curScope.addDecl(name, this);
    }

    @Override public String identify() {
        if (lineNum == 0)
            return "<type decl> " + name + " in the library";

        return "<type decl> " + name + " on line " + lineNum;
    }

    static TypeDecl parse(Scanner s){
        enterParser("type decl");

        leaveParser("type decl");
        return null;
    }

    void checkWhetherAssignable(PascalSyntax where){

    }
    void checkWhetherFunction(PascalSyntax where){
        where.error("TypeDecl - Not a func");
    }
    void checkWhetherProcedure(PascalSyntax where){
        where.error("TypeDecl - Not a proc");
    }
    void checkWhetherValue(PascalSyntax where){
        where.error("TypeDecl - Not a value");
    }
}
