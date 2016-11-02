package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

class NumberLiteral extends UnsignedConstant {

    int number;

    NumberLiteral(int lNum) {
        super(lNum);
    }

    @Override void check(Block curScope, Library lib) {
        type = lib.intType;
        constVal = number;
    }

    @Override public String identify() {
        return "<NumberLiteral> on line " + lineNum;
    }

    @Override public void prettyPrint() {
        Main.log.prettyPrint(Integer.toString(number));
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
