package parser;

import main.*;
import scanner.*;
import java.util.ArrayList;
import static scanner.TokenKind.*;

class VarDeclPart extends PascalSyntax {

    ArrayList<VarDecl> vd;

    VarDeclPart(int lNum) {
        super(lNum);
        vd = new ArrayList<>();
    }

    @Override public String identify() {
        return "<VarDeclPart> on line " + lineNum;
    }

    @Override public void prettyPrint() {

    }

    // Pretty print here
    static VarDeclPart parse(Scanner s) {
        enterParser("var decl part");

        VarDeclPart vdp = new VarDeclPart(s.curLineNum());

        s.skip(varToken);

        while (true) {
            if (s.curToken.kind == nameToken) {
                vdp.vd.add(VarDecl.parse(s));
            } else {
                break;
            }
        }

        leaveParser("var decl part");
        return vdp;
    }
}
