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

        try {
            ex.ro = RelOperator.parse(s);
            ex.se.add(SimpleExpr.parse(s));
        } catch (PascalError e) {
            System.out.println("Error occured in <Expression>: " + e.getMessage());
        }

        leaveParser("expression");
        return ex;
    }
}
