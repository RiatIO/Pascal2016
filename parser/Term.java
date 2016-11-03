package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;
import java.util.ArrayList;

class Term extends PascalSyntax {
    ArrayList<Factor> f;
    ArrayList<FactorOperator> fo;

    types.Type type;

    Term(int lNum) {
        super(lNum);
        f  = new ArrayList<>();
        fo = new ArrayList<>();
    }

    @Override void check(Block curScope, Library lib) {
        for (int i = 0; i < f.size(); i++) {
            f.get(i).check(curScope, lib);
            type = f.get(i).type;

            if (i != 0) {
                fo.get(i-1).check(curScope, lib);
                String opr = fo.get(i-1).k.toString();
                type.checkType(fo.get(i-1).type, "left " + opr + " operand", this, "something");
                type.checkType(f.get(i).type, "right " + opr + " operand", this, "something");
            }
        }
    }

    @Override public String identify() {
        return "<statmlist> on line " + lineNum;
    }

    @Override public void prettyPrint() {
        for (int i = 0; i < f.size(); i++) {
            if (i != 0) {
                Main.log.prettyPrint(" ");
                fo.get(i-1).prettyPrint();
                Main.log.prettyPrint(" ");
            }
            f.get(i).prettyPrint();
        }
    }

    static Term parse(Scanner s) {
        enterParser("term");

        Term t = new Term(s.curLineNum());

        while (true) {
            t.f.add(Factor.parse(s));

            if (s.curToken.kind.isFactorOpr()) {
                t.fo.add(FactorOperator.parse(s));
            } else {
                break;
            }
        }

        leaveParser("term");
        return t;
    }
}
