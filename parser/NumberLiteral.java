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
        enterParser("NumberLiteral");

        NumberLiteral nl = new NumberLiteral(s.curLineNum());

        s.test(intValToken);
        nl.number = s.curToken.intVal;
        s.readNextToken();

        leaveParser("NumberLiteral");
        return nl;
    }
}
