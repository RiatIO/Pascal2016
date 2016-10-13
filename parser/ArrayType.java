package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;
import java.util.ArrayList;

class ArrayType extends Type {
    Type t;
<<<<<<< HEAD
    Constant cFirst;
    Constant cSecond;
=======
    Constant preC;
    Constant postC;
>>>>>>> 2383d82e46b7d71a889a587a4f9cafabbf009a13

    ArrayType(int lNum) {
        super(lNum);
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
<<<<<<< HEAD
        enterParser("ArrayType");

=======
        enterParser("array-type");
>>>>>>> 2383d82e46b7d71a889a587a4f9cafabbf009a13
        ArrayType at = new ArrayType(s.curLineNum());

        s.skip(arrayToken);
        s.skip(leftBracketToken);

<<<<<<< HEAD
        at.cFirst = Constant.parse(s);
=======
        at.preC = Constant.parse(s);
>>>>>>> 2383d82e46b7d71a889a587a4f9cafabbf009a13
        s.skip(rangeToken);
        at.cSecond = Constant.parse(s);

<<<<<<< HEAD
=======
        at.postC = Constant.parse(s);
>>>>>>> 2383d82e46b7d71a889a587a4f9cafabbf009a13
        s.skip(rightBracketToken);

        s.skip(ofToken);
        at.t = Type.parse(s);

        leaveParser("array-type");
        return at;
    }
}
