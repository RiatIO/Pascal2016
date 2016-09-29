package parser;

import main.*;
import scanner.*;
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

        boolean isThereMore = false;

        while (!isThereMore) {
            cdp.cd.insert(ConstDecl.parse(s));

            try {
                s.test(nameToken);
                s.readNextToken();
                s.test(equalToken);
            } catch (PascalError e) {
                error(e);
            }
        }

        leaveParser("const-decl-part");
        return b;
    }
}
