package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

abstract class StatmList extends PascalSyntax {
    StatmList(int lNum) {
        super(lNum);
    }

    @Override public String identify() {
        return "<StatmList> on line " + lineNum;
    }

    @Override public void prettyPrint() {

    }

    static StatmList parse(Scanner s) {
        leaveParser("StatmList");

        leaveParser("StatmList");
        return null;
    }
}
