package parser;

import main.*;
import scanner.*;
import java.util.ArrayList;
import static scanner.TokenKind.*;

class ProcDecl extends PascalDecl {
    ParamDeclList pdl;
    Block b;

    ProcDecl(String id, int lNum){
        super(id, lNum);
    }

    @Override public String identify() {
        return "<Param-decl> " + name + "on line " + lineNum
    }

    static ProcDecl parse(Scanner s) {
        enterParser("Proc-decl");
        s.skip(procedureToken);
        s.test(nameToken);

        ProcDecl pd = new ProcDecl(s.curToken.id, s.curLineNum());

        // TODO: is this even rigt?
        if (s.curToken.kind != leftParToken) {
            pd.pdl = ParamDeclList.parse(s);
        }

        s.skip(semicolonToken);
        pd.b = Block.parse(s);
        s.skip(semicolonToken);

        leaveParser("Param-decl");
        return pd;
    }

}