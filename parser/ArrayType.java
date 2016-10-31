package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;
import java.util.ArrayList;

class ArrayType extends Type {
    Type t;
    Constant preC;
    Constant postC;

    types.Type type;

    ArrayType(int lNum) {
        super(lNum);
    }

    @Override void check(Block curScope, Library lib) {
    }

    @Override public String identify() {
        return "<ArrayType> on line " + lineNum;
    }

    @Override public void prettyPrint() {
        Main.log.prettyPrint("array [");
        preC.prettyPrint();
        Main.log.prettyPrint("..");
        postC.prettyPrint();
        Main.log.prettyPrint("] of ");
        t.prettyPrint();
    }

    static ArrayType parse(Scanner s) {
        enterParser("array-type");
        ArrayType at = new ArrayType(s.curLineNum());

        s.skip(arrayToken);
        s.skip(leftBracketToken);

        at.preC = Constant.parse(s);
        s.skip(rangeToken);

        at.postC = Constant.parse(s);
        s.skip(rightBracketToken);

        s.skip(ofToken);
        at.t = Type.parse(s);

        leaveParser("array-type");
        return at;
    }
}
