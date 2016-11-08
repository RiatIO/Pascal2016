package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

class InnerExpr extends Factor {

    Expression expr;

    InnerExpr(int lNum) {
        super(lNum);
    }

    @Override void genCode(CodeFile f) {

	}

	@Override void check(Block curScope, Library lib) {
        expr.check(curScope, lib);
        type = expr.type;
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
