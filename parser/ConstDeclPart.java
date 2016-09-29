package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

class ConstDeclPart extends PascalSyntax {

    ConstDecl cd;

    ConstDeclPart(int lNum) {
        super(lNum);
    }

    @Override public String identify() {
        return "<const-decl-part> on line " + lineNum;
    }

    static ConstDeclPart parse(Scanner s) {
        enterParser("const-decl-part");

        s.skip(constToken);

        ConstDeclPart cdp = new ConstDeclPart(s.curLineNum());


        try {
            cdp.cd = ConstDecl.parse(s);
        } catch(PascalError e) {
            System.out.println("something");
        }


        leaveParser("const-decl-part");
        return b;
    }

}
