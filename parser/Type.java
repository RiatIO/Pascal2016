package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

abstract class Type extends PascalSyntax {
    Type(int lNum) {
        super(lNum);
    }

    @Override public String identify() {
        return "<type> on line " + lineNum;
    }

    @Override public void prettyPrint() {

    }

    static Type parse(Scanner s) {
        leaveParser("type");

        leaveParser("type");
        return null;
    }
}
