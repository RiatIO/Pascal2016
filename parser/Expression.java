package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

import java.util.ArrayList;

class Expression extends PascalSyntax {
    RelOperator ro;
    SimpleExpr preSe, postSe;

    Expression(int lNum) {
        super(lNum);
    }

    @Override public String identify() {
        return "<expression> on line " + lineNum;
    }

    @Override public void prettyPrint() {
        preSe.prettyPrint();

        if (ro != null) {
            Main.log.prettyPrint(" ");
            ro.prettyPrint();
            Main.log.prettyPrint(" ");
            postSe.prettyPrint();
        }
    }

    static Expression parse(Scanner s) {
        enterParser("expression");

        Expression ex = new Expression(s.curLineNum());
        ex.preSe = SimpleExpr.parse(s);

        if (s.curToken.kind.isRelOpr()) {
            ex.ro = RelOperator.parse(s);
            ex.postSe = SimpleExpr.parse(s);
        }

        leaveParser("expression");
        return ex;
    }
}
