package parser;

import main.*;

import scanner.*;
import java.util.ArrayList;
import static scanner.TokenKind.*;

class FuncDecl extends ProcDecl {
    ParamDeclList pdl;
    Block b;
    Type type;

    FuncDecl(String id, int lNum) {
        super(id, lNum);
    }

    @Override public String identify() {
        return "<Func-decl> "+ name + "on line " + lineNum;
    }

    static FuncDecl parse(Scanner s) {
        enterParser("Func-decl");
        s.skip(functionToken);
        s.test(nameToken);

        FuncDecl fd = new FuncDecl(s.curToken.id, s.curLineNum());

        if (s.curToken.kind != colonToken) {
            fd.pdl = ParamDeclList.parse(s);
        }

        s.skip(colonToken);
        fd.type = Type.parse(s);
        s.skip(semicolonToken);

        fd.b = Block.parse(s);
        s.skip(semicolonToken);

        leaveParser("Func-decl");
        return fd;

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
