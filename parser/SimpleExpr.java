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
        t  = new ArrayList<>();
        to = new ArrayList<>();
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
            Term t = Term.parse(s);
            se.t.add(t);

            if (s.curToken.kind.isTermOpr()) {
                se.to.add(TermOperator.parse(s));
            } else {
                break;
            }
        }

        leaveParser("simple expr");
        return se;
    }
}
