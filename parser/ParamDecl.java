package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

class ParamDecl extends PascalDecl {
    Type type;

    PascalDecl(String id, int lNum) {

    }

    @Override public String identify() {
        return "<Param-decl> " + name + "on line " + lineNum;
    }

    @Override public void prettyPrint() {

    }

    static ParamDecl parse(Scanner s) {
        enterParser("Param-decl");
        s.test(nameToken);

        ParamDecl pd = new ParamDecl(s.curToken.id, s.curLineNum());
        s.skip(colonToken);

        pd.type = Type.parse(s);
        leaveParser("Param-decl");
        return pd;
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
