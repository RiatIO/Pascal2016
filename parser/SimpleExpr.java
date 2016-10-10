package parser;

import main.*;
import scanner.*;
import java.util.ArrayList;
import static scanner.TokenKind.*;

class SimpleExpr extends PascalSyntax {
    SimpleExpr(int lNum) {
        super(lNum);
    }

    @Override public String identify() {
        return "<SimpleExpr> on line " + lineNum;
    }

    @Override public void prettyPrint() {

    }

    static SimpleExpr parse(Scanner s) {
        enterParser("SimpleExpr");


        leaveParser("SimpleExpr");
        return null;
    }
}
