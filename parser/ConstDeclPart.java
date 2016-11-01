package parser;

import main.*;
import scanner.*;
import java.util.ArrayList;
import static scanner.TokenKind.*;

class ConstDeclPart extends PascalSyntax {

    ArrayList<ConstDecl> cd;

    types.Type type;

    ConstDeclPart(int lNum) {
        super(lNum);
        cd = new ArrayList<>();
    }

    @Override void check(Block curScope, Library lib) {
        for (ConstDecl c : cd) {
            c.check(curScope, lib);
        }
    }

    @Override public String identify() {
        return "<const decl part> on line " + lineNum;
    }

    // Pretty print here
    @Override public void prettyPrint() {
        Main.log.prettyPrint("const");
        Main.log.prettyPrintLn();
        Main.log.prettyIndent();

        for (int i = 0; i < cd.size(); i++) {
            if (i != 0) {
                Main.log.prettyPrintLn();
            }
            cd.get(i).prettyPrint();
        }

        Main.log.prettyPrintLn();
        Main.log.prettyOutdent();
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
