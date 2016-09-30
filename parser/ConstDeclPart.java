package parser;

import main.*;
import scanner.*;
import java.util.ArrayList;
import static scanner.TokenKind.*;

class ConstDeclPart extends PascalSyntax {

    ArrayList<ConstDecl> cd;

    ConstDeclPart(int lNum) {
        super(lNum);
    }

    @Override public String identify() {
        return "<const-decl-part> on line " + lineNum;
    }

    // Pretty print here
    static ConstDeclPart parse(Scanner s) {
        enterParser("const-decl-part");

        s.skip(constToken);

        ConstDeclPart cdp = new ConstDeclPart(s.curLineNum());

        while (true) {
            try {
                ConstDecl temp = ConstDecl.parse(s);
                cdp.cd.insert(temp);
            } catch (PascalError e) {
                break;
            }
        }

        // TODO: Check if arraylis is empty or not.

        leaveParser("const-decl-part");
        return cdp;
    }
}
