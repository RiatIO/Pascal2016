package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

class ParamDecl extends PascalDecl {
    TypeName tn;

    ParamDecl(String id, int lNum) {
        super(id, lNum);
    }

    @Override void check(Block curScope, Library lib) {
        curScope.addDecl(name, this);
        tn.check(curScope, lib);
    }

    @Override public String identify() {
        return "<param decl> " + name + " on line " + lineNum;
    }

    @Override public void prettyPrint() {
        Main.log.prettyPrint(name + ": ");
        tn.prettyPrint();
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
    //TODO: Find out which one to leave empty, maybe it is VAlue!
    @Override void checkWhetherAssignable(PascalSyntax where){
        where.error("You cannot assign a constant");
    }
    @Override void checkWhetherFunction(PascalSyntax where){
        where.error("You cannot func");
    }

    @Override void checkWhetherProcedure(PascalSyntax where){
        where.error("You cannot proc");
    }
    @Override void checkWhetherValue(PascalSyntax where){

    }
}
