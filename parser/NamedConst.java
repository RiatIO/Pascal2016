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

    @Override public void prettyPrint() {
        Main.log.prettyPrint(name);
    }

    static NamedConst parse(Scanner s) {
        enterParser("named constant");

        NamedConst nc = new NamedConst(s.curLineNum());

        s.test(nameToken);
        nc.name = s.curToken.id;
        s.skip(s.curToken.kind);

        leaveParser("named constant");
        return nc;
    }
}
