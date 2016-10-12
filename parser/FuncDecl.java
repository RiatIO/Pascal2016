package parser;

import main.*;

import scanner.*;
import java.util.ArrayList;
import static scanner.TokenKind.*;

class FuncDecl extends ProcDecl {
    ParamDeclList pdl;
    Block b;
    TypeName tn;

    FuncDecl(String id, int lNum) {
        super(id, lNum);
    }

    @Override public String identify() {
        return "<Func-decl> "+ name + "on line " + lineNum;
    }

    static FuncDecl parse(Scanner s) {
        enterParser("func decl");
        s.skip(functionToken);
        s.test(nameToken);

        FuncDecl fd = new FuncDecl(s.curToken.id, s.curLineNum());

        s.readNextToken();

        if (s.curToken.kind != colonToken) {
            fd.pdl = ParamDeclList.parse(s);
        }

        s.skip(colonToken);
        fd.tn = TypeName.parse(s);
        s.skip(semicolonToken);

        fd.b = Block.parse(s);
        s.skip(semicolonToken);

        leaveParser("func decl");
        return fd;
    }
}
