package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;
import java.util.ArrayList;

class Block extends PascalSyntax {
    Program context;

    ConstDeclPart cdp;
    VarDeclPart vdp;

    ArrayList<FuncDecl> fd;
    ArrayList<ProcDecl> pd;
    StatmList sm;

    Block(int lNum) {
        super(lNum);
    }

    @Override public String identify() {
        return "<block> on line " + lineNum;
    }

    @Override public void prettyPrint() {

    }

    static Block parse(Scanner s) {
        enterParser("block");

        Block b = new Block(s.curLineNum());

        if (s.curToken.kind == constToken) {
            b.cdp = ConstDeclPart.parse(s);
        }

        if (s.curToken.kind == varToken) {
            b.vdp = VarDeclPart.parse(s);
        }

        while(true) {
            if (s.curToken.kind == functionToken) {
                b.fd.add(FuncDecl.parse(s));
            } else if (s.curToken.kind == procedureToken) {
                b.pd.add(ProcDecl.parse(s));
            } else {
                break;
            }
        }

        s.skip(beginToken);

        b.sm = StatmList.parse(s);

        s.skip(endToken);

        leaveParser("block");
        return b;
    }
}
