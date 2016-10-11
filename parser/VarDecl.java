package parser;

import main.*;
import scanner.*;
import java.util.ArrayList;
import static scanner.TokenKind.*;

class VarDecl extends PascalDecl{
    Type type;

    VarDecl(String id, int lNum) {
        super(id, lNum);
    }

    @Override public String identify() {
        return "<Var-decl> " + name + "on line " + lineNum;
    }

    static VarDecl parse(Scanner s) {
        enterParser("Var-decl");
        s.test(nameToken);

        VarDecl vd = new VarDecl(s.curToken.id, s.curLineNum());
        
        s.skip(colonToken);
        vd.type = Type.parse(s);
        s.skip(semicolonToken);
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
