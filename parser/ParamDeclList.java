package parser;

import main.*;
import scanner.*;
import java.util.ArrayList;
import static scanner.TokenKind.*;

class ParamDeclList extends PascalSyntax {

    ArrayList<ParamDecl> pd;

    ParamDeclList(int lNum) {
        super(lNum);
        pd = new ArrayList<>();
    }

    @Override void genCode(CodeFile f) {

	}

	@Override void check(Block curScope, Library lib) {
        int size = 4;
        for (ParamDecl p : pd) {
            p.check(curScope, lib);
            size += p.type.size();
            p.declOffset = size;
        }
    }

    @Override public String identify() {
        return "<param-decl-list> on line " + lineNum;
    }

    @Override public void prettyPrint() {

        Main.log.prettyPrint("(");

        for (int i = 0; i < pd.size(); i++) {
            if (i != 0) {
                Main.log.prettyPrint("; ");
            }
            pd.get(i).prettyPrint();
        }
        Main.log.prettyPrint(")");

    }

    static ParamDeclList parse(Scanner s) {
        enterParser("param decl list");

        ParamDeclList pdl = new ParamDeclList(s.curLineNum());

        s.skip(leftParToken);

        while (true) {
            pdl.pd.add(ParamDecl.parse(s));

            if (s.curToken.kind == semicolonToken) {
                s.skip(semicolonToken);
            } else {
                break;
            }
        }

        s.skip(rightParToken);

        leaveParser("param decl list");
        return pdl;
    }
}
