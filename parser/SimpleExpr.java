package parser;

import main.*;
import scanner.*;
import java.util.ArrayList;
import static scanner.TokenKind.*;

class SimpleExpr extends PascalSyntax {
    PrefixOperator po;
    ArrayList<Term> t;
    ArrayList<TermOperator> to;

    types.Type type;

    SimpleExpr(int lNum) {
        super(lNum);
        t  = new ArrayList<>();
        to = new ArrayList<>();
    }

    @Override void check(Block curScope, Library lib) {
        if (po != null)
            po.check(curScope, lib);

        for (int i = 0; i < t.size(); i++) {
            t.get(i).check(curScope, lib);
            type = t.get(i).type;

            if (i != 0) {
                to.get(i-1).check(curScope, lib);

                String opr = to.get(i-1).tk.toString();
                type.checkType(to.get(i-1).type, "left " + opr + " operand", this, "something");
                type.checkType(t.get(i).type, "right " + opr + " operand", this, "something");
            }
        }
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

    void checkWhetherAssignable(PascalSyntax where){

    }
    void checkWhetherFunction(PascalSyntax where){

    }
    void checkWhetherProcedure(PascalSyntax where){

    }
    void checkWhetherValue(PascalSyntax where){

    }
}
