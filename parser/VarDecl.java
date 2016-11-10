package parser;

import main.*;
import scanner.*;
import java.util.ArrayList;
import static scanner.TokenKind.*;

class VarDecl extends PascalDecl {
    Type t;

    VarDecl(String id, int lNum) {
        super(id, lNum);
    }

    @Override void genCode(CodeFile f) {
        // f.genInstr("", "movl", "-4(%ebp),%edx", "");
        // f.genInstr("", "movl", "-" + declOffset + "(%edx),%eax", "    " + name);
	}

	@Override void check(Block curScope, Library lib) {
        curScope.addDecl(name, this);
        t.check(curScope, lib);
        type = t.type;
        declLevel = curScope.blockId;
        declOffset = 32;
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
        where.error("Var - Not a func");
    }
    void checkWhetherProcedure(PascalSyntax where){
        where.error("Var - Not a proc");
    }
    void checkWhetherValue(PascalSyntax where){

    }
}
