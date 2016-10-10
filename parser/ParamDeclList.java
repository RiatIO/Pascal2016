package parser;

import main.*;
import scanner.*;
import java.util.ArrayList;
import static scanner.TokenKind.*;

class ParamDeclList extends PascalSyntax {

    ArrayList<ParamDecl> pd;

    ParamDeclList(int lNum) {
        super(lNum);
    }

    @Override public String identify() {
        return "<param-decl-list> on line " + lineNum;
    }

    @Override public void prettyPrint() {

    }

    static ParamDeclList parse(Scanner s) {
        enterParser("param-decl-list");

        s.skip(leftParToken);

        ParamDeclList pdl = new ParamDeclList(s.curLineNum());

        while (true) {
            try {
                pdl.pd.add(ParamDecl.parse(s));
                s.test(semicolonToken);
            } catch(PascalError e) {
                break;
            }
        }

        s.skip(rightParToken);

        leaveParser("param-decl-list");
        return pdl;
    }
}
