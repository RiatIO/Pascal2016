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
    
    @Override void check(Block curScope, Library lib) {
        leftOp.check(curScope, lib);
        type = leftOp.type;

        if (rightOp != null) {
            rightOp.check(curScope, lib);
            String oprName = relOpr.opr.kind.toString();
            type.checkType(rightOp.type, oprName+" operands", this,
                "Operands to "+oprName+" are of different type!");
            type = lib.booleanType;
        }
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
