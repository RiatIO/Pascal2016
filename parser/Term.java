package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;
import java.util.ArrayList;

class Term extends PascalSyntax {
    ArrayList<Factor> f;
    ArrayList<FactorOperator> fo;

    Term(int lNum) {
        super(lNum);
        f  = new ArrayList<>();
        fo = new ArrayList<>();
    }

    @Override public String identify() {
        return "<statmlist> on line " + lineNum;
    }

    @Override public void prettyPrint() {

    }

    static Term parse(Scanner s) {
        enterParser("term");

        Term t = new Term(s.curLineNum());

        while (true) {
            try {
                Factor temp = Factor.parse(s);
                t.f.add(temp);
            } catch(PascalError e) {
                System.out.println("DEBUG TRAIN ON THE RUN");
            }

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
