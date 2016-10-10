package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

class NamedConst extends UnsignedConstant {

    String name;

    NamedConst(int lNum) {
        super(lNum);
    }

    @Override public String identify() {
        return "<namedconst> on line " + lineNum;
    }

    static NamedConst parse(Scanner s) {
        enterParser("namedconst");

        NamedConst nc = new NamedConst(s.curLineNum());

        s.test(nameToken);
        nc.name = s.curToken.id;
        s.readNextToken();

        leaveParser("namedconst");
        return nc;
    }
}
