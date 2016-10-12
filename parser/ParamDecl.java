package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

class ParamDecl extends PascalDecl {
    TypeName tn;

    ParamDecl(String id, int lNum) {
        super(id, lNum);
    }

    @Override public String identify() {
        return "<Param-decl> " + name + "on line " + lineNum;
    }

    @Override public void prettyPrint() {

    }

    static ParamDecl parse(Scanner s) {
        enterParser("param decl");
        s.test(nameToken);

        ParamDecl pd = new ParamDecl(s.curToken.id, s.curLineNum());
        s.readNextToken();

        s.skip(colonToken);

        pd.tn = TypeName.parse(s);
        leaveParser("param decl");
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
