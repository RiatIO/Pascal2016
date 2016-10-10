package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;
import java.util.ArrayList;

class ProcCallStatm extends Statement{
    ArrayList<Expression> expr;
    String name;

    ProcCallStatm(int lNum){
        super(lNum);
    }

    @Override public String identify() {
        return "<ProcCall-statm> on line " + lineNum;
    }

    @Override public void prettyPrint() {

    }

    static ProcCallStatm parse(Scanner s){
        enterParser("proc call");

        ProcCallStatm pc = new ProcCallStatm(s.curLineNum());
        s.test(nameToken);
        pc.name = s.curToken.id;
        s.skip(nameToken);

        if (s.curToken.kind == leftParToken) {
            s.skip(leftParToken);

            while (true){
                pc.expr.add(Expression.parse(s));

                if (s.curToken.kind != commaToken) {
                    break;
                }

                s.skip(commaToken);
            }
            s.skip(rightParToken);
        }

        leaveParser("proc call");
        return pc;
    }
}
