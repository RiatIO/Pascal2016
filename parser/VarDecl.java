package parser;

import main.*;
import scanner.*;
import java.util.ArrayList;
import static scanner.TokenKind.*;

class VarDecl extends PascalDecl{
    Type t;

    VarDecl(String id, int lNum) {
        super(id, lNum);
    }

    @Override void check(Block curScope, Library lib) {
        curScope.addDecl(name, this);
        t.check(curScope, lib);
    }

    @Override public String identify() {
        return "<var decl> " + name + " on line " + lineNum;
    }

    @Override public void prettyPrint() {
        Main.log.prettyPrint(name + ": ");
        t.prettyPrint();
        Main.log.prettyPrint(";");
    }

    static VarDecl parse(Scanner s) {
        enterParser("var decl");
        s.test(nameToken);
        VarDecl vd = new VarDecl(s.curToken.id, s.curLineNum());
        s.readNextToken();

        s.skip(colonToken);
        vd.t = Type.parse(s);
        s.skip(semicolonToken);

        leaveParser("var decl");
        return vd;
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
