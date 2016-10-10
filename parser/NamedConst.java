package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

class NamedConst extends UnsignedConstant {

    char c;

    InnerExpr(int lNum) {
        super(lNum);
    }

    @Override public String identify() {
        return "<namedconst> on line " + lineNum;
    }

    static NamedConst parse(Scanner s) {
        enterParser("namedconst");

        s.skip(commaToken);

        NamedConst nc = new NamedConst(s.curLineNum());

        leaveParser("namedconst");
        return cl;
    }
}
