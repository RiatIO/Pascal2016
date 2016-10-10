package parser;

import main.*;
import scanner.*;
import java.util.ArrayList;
import static scanner.TokenKind.*;

class Constant extends PascalSyntax {

    Constant(int lNum) {
        super(lNum);
    }

    @Override public String identify() {
        return "<Constant> on line " + lineNum;
    }

    @Override public void prettyPrint() {

    }

    // Pretty print here

    static Constant parse(Scanner s) {
        enterParser("Constant");

        leaveParser("Constant");
        return null;
    }
}
