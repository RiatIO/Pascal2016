package parser;

import main.*;
import scanner.*;
import java.util.ArrayList;
import static scanner.TokenKind.*;

class ConstDeclPart extends PascalSyntax {

    ArrayList<ConstDecl> cd;

    ConstDeclPart(int lNum) {
        super(lNum);
        cd = new ArrayList<>();
    }

    @Override public String identify() {
        return "<const decl part> on line " + lineNum;
    }

    // Pretty print here
    @Override public void prettyPrint() {

    }

    static ConstDeclPart parse(Scanner s) {
        enterParser("const decl part");

        ConstDeclPart cdp = new ConstDeclPart(s.curLineNum());
        s.skip(constToken);

        while (true) {
            if (s.curToken.kind == nameToken) {
                ConstDecl temp = ConstDecl.parse(s);
                cdp.cd.add(temp);
            } else {
                break;
            }
        }

        leaveParser("const decl part");
        return cdp;
    }
}
