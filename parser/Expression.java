package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

import java.util.ArrayList;

class Expression extends PascalSyntax {
    ArrayList<SimpleExpr> se;
    RelOperator ro;

    Expression(int lNum) {
        super(lNum);
    }

    @Override public String identify() {
        return "<expression> on line " + lineNum;
    }

    @Override public void prettyPrint() {

    }

    static Expression parse(Scanner s) {
        enterParser("expression");

        Expression ex = new Expression(s.curLineNum());
        ex.se.add(SimpleExpr.parse(s));

        if (s.curToken.kind.isRelOpr()) {
            ex.ro = RelOperator.parse(s);
            ex.se.add(SimpleExpr.parse(s));
        }

        leaveParser("expression");
        return ex;
    }
}
