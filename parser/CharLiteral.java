package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

class CharLiteral extends UnsignedConstant {

    char c;

    CharLiteral(int lNum) {
        super(lNum);
    }

    @Override public String identify() {
        return "<charliteral> on line " + lineNum;
    }

    static CharLiteral parse(Scanner s) {
        enterParser("charliteral");

        //s.skip(commaToken);

        CharLiteral cl = new CharLiteral(s.curLineNum());

        //s.test(s.curToken.charVal);

        leaveParser("charliteral");
        return cl;
    }
}
