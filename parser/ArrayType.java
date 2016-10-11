package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;
import java.util.ArrayList;

class ArrayType extends Type {
    Type t;
    Constant preC;
    Constant postC;

    ArrayType(int lNum) {
        super(lNum);
    }

    @Override public String identify() {
        return "<ArrayType> on line " + lineNum;
    }

    @Override public void prettyPrint() {

    }

    static ArrayType parse(Scanner s) {
        enterParser("array type");
        ArrayType at = new ArrayType(s.curLineNum());

        s.skip(arrayToken);
        s.skip(leftBracketToken);

        at.preC = Constant.parse(s);
        s.skip(rangeToken);

        at.postC = Constant.parse(s);
        s.skip(rightBracketToken);

        s.skip(ofToken);
        at.t = Type.parse(s);

        leaveParser("array type");
        return at;
    }
}
