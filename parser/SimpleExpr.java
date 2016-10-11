package parser;

import main.*;
import scanner.*;
import java.util.ArrayList;
import static scanner.TokenKind.*;

class SimpleExpr extends PascalSyntax {
    PrefixOperator po;

    // FIIIIIX THIS TOMORROWOOWOWW

    SimpleExpr(int lNum) {
        super(lNum);
    }

    @Override public String identify() {
        return "<SimpleExpr> on line " + lineNum;
    }

    @Override public void prettyPrint() {

    }

    static SimpleExpr parse(Scanner s) {
        enterParser("simple expr");

        SimpleExpr se = new SimpleExpr(s.curLineNum());

        if (s.curToken.kind.isPrefixOpr()) {
            se.po = PrefixOperator.parse(s);
        }

        

        leaveParser("simple expr");
        return se;
    }
}
