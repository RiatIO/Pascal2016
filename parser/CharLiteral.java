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

        CharLiteral cl = new CharLiteral(s.curLineNum());

        System.out.println(s.curToken.kind);

        s.test(charValToken);
        cl.c = s.curToken.charVal;
        s.skip(s.curToken.kind);

        leaveParser("charliteral");
        return cl;
    }
}
