package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

class NumberLiteral extends UnsignedConstant {

    int number;

    NumberLiteral(int lNum) {
        super(lNum);
    }

    @Override public String identify() {
        return "<NumberLiteral> on line " + lineNum;
    }

    static NumberLiteral parse(Scanner s) {
        enterParser("number literal");

        NumberLiteral nl = new NumberLiteral(s.curLineNum());

        s.test(intValToken);
        nl.number = s.curToken.intVal;
        s.skip(s.curToken.kind);

        leaveParser("number literal");
        return nl;
    }
}
