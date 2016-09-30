package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

class Expression extends PascalSyntax {
    ArrayList<SimpleExpr> se;
    RelOperator ro;

    Expression(int lNum) {
        super(lNum);
    }

    @Override public String identify() {
        return "<expression> on line " + lineNum;
    }

    static Expression parse(Scanner s) {
        enterParser("expression");

        Expression ex = new Expression(s.curLineNum());
        ex.se.add(SimpleExpr.parse(s));

        try {
            // TODO: fix this
            ex.ro = RelOperator.parse(s);
            ex.se.add(SimpleExpr.parse(s));
        } catch (PascalError e) {

        }

        leaveParser("expression");
        return ex;
    }
}
