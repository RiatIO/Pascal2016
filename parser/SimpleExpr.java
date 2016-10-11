package parser;

import main.*;
import scanner.*;
import java.util.ArrayList;
import static scanner.TokenKind.*;

class SimpleExpr extends PascalSyntax {
    PrefixOperator po;
    ArrayList<Term> t;
    ArrayList<TermOperator> to;

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

        while (true) {
            try {
                se.t.add(Term.parse(s));
            } catch (PascalError e) {
                break;
            }

            if (s.curToken.kind.isTermOpr()) {
                se.to.add(TermOperator.parse(s));
            }
        }

        leaveParser("simple expr");
        return se;
    }
}
