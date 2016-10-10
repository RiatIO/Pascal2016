package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

class CharLiteral extends UnsignedConstant {

    char c;

    InnerExpr(int lNum) {
        super(lNum);
    }

    @Override public String identify() {
        return "<charliteral> on line " + lineNum;
    }

    static CharLiteral parse(Scanner s) {
        enterParser("charliteral");

        s.skip(commaToken);

        NamedConst nc = new NamedConst(s.curLineNum());
        
        s.test(s.curToken.charVal)

        leaveParser("charliteral");
        return cl;
    }
}
