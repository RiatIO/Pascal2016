package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;
import java.util.ArrayList;

class ArrayType extends Type {
    Type t;
    Constant cFirst;
    Constant cSecond;

    ArrayType(int lNum) {
        super(lNum);
    }

    @Override public String identify() {
        return "<ArrayType> on line " + lineNum;
    }

    @Override public void prettyPrint() {

    }

    static ArrayType parse(Scanner s) {
        enterParser("ArrayType");

        ArrayType at = new ArrayType(s.curLineNum());

        s.skip(arrayToken);
        s.skip(leftBracketToken);

        at.cFirst = Constant.parse(s);
        s.skip(rangeToken);
        at.cSecond = Constant.parse(s);

        s.skip(rightBracketToken);

        s.skip(ofToken);
        at.t = Type.parse(s);

        leaveParser("ArrayType");
        return at;
    }
}
