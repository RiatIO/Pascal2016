package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

class InnerExpr extends Factor {

    Expression expr;

    types.Type type;

    InnerExpr(int lNum) {
        super(lNum);
    }

    @Override void check(Block curScope, Library lib) {
    }

    @Override public String identify() {
        return "<innerexpr> on line " + lineNum;
    }

    @Override public void prettyPrint() {
        Main.log.prettyPrint("(");
        expr.prettyPrint();
        Main.log.prettyPrint(")");
    }

    static InnerExpr parse(Scanner s) {
        enterParser("inner expr");

        s.skip(leftParToken);

        InnerExpr ie = new InnerExpr(s.curLineNum());

        ie.expr = Expression.parse(s);

        s.skip(rightParToken);

        leaveParser("inner expr");
        return ie;
    }
}
