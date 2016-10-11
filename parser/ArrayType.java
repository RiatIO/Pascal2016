package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;
import java.util.ArrayList;

class ArrayType extends Type {
    Type t;
    Constant c;

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
        s.skip(arrayToken);
        s.skip(leftBracketToken);

        t.c = Constant.parse(s);
        s.skip(rangeToken);

        t.c = Constant.parse(s);
        s.skip(rightBracketToken);

        s.skip(ofToken);
        t = Type.parse(s);

        leaveParser("ArrayType");
        return sl;
    }
}
