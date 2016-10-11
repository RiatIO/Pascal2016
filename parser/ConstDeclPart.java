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
    @Override public void prettyPrint() {

    }

    static ConstDeclPart parse(Scanner s) {
        enterParser("const-decl-part");

        ConstDeclPart cdp = new ConstDeclPart(s.curLineNum());
        s.skip(constToken);

        while (true) {
            try {
                ConstDecl temp = ConstDecl.parse(s);
                cdp.cd.add(temp);
            } catch (PascalError e) {
                break;
            }
        }

        if (cdp.cd.isEmpty()) {
            //throw new PascalError("No ConstDecl found!");
        }

        leaveParser("const-decl-part");
        return cdp;
    }
}
