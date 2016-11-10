package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

class ParamDecl extends PascalDecl {
    TypeName tn;

    ParamDecl(String id, int lNum) {
        super(id, lNum);
    }

    @Override void genCode(CodeFile f) {
        f.genInstr("", "movl", "-4(%ebp),%edx", "");
        f.genInstr("", "movl", "-" + declOffset + "(%edx),%eax", " " + name);
	}

	@Override void check(Block curScope, Library lib) {
        curScope.addDecl(name, this);
        tn.check(curScope, lib);
        type = tn.type;
        declLevel = curScope.blockId;
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
        where.error("Param - You cannot assign a constant");
    }

    @Override void checkWhetherFunction(PascalSyntax where){
        where.error("Param - You cannot func");
    }

    @Override void checkWhetherProcedure(PascalSyntax where){
        where.error("Param  - You cannot proc");
    }

    @Override void checkWhetherValue(PascalSyntax where){

    }
}
