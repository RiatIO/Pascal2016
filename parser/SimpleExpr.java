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
        if (po != null) {
            po.prettyPrint();
        }
        for (int i = 0; i < t.size(); i++) {
            if (i != 0) {
                Main.log.prettyPrint(" ");
                to.get(i-1).prettyPrint();
                Main.log.prettyPrint(" ");
            }
            t.get(i).prettyPrint();
        }
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
