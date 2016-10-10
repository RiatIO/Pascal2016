package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

class InnerExpr extends Factor {

    Expression expr;

    InnerExpr(int lNum) {
        super(lNum);
    }

    @Override public String identify() {
        return "<innerexpr> on line " + lineNum;
    }

    static InnerExpr parse(Scanner s) {
        enterParser("innerexpr");

        s.skip(leftParToken);

        InnerExpr ie = new InnerExpr(s.curLineNum());

        ie.expr = Expression.parse(s);

        s.skip(rightParToken);

        leaveParser("innerexpr");
        return ie;
    }
}
