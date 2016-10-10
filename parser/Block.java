package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

class Block extends PascalSyntax {
    Program context;

    ConstDeclPart cdp;
    VarDeclPart vdp;
    FuncDecl fd;
    ProcDecl pd;
    StatmList sm;

    Block(int lNum) {
        super(lNum);
    }

    @Override public String identify() {
        return "<block> on line " + lineNum;
    }

    static Block parse(Scanner s) {
        enterParser("block");

        leaveParser("block");
        return null;
    }
}
