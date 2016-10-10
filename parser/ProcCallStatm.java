package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

class ProcCallStatm extends Statement{
    Expression expr;
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
        enterParser("ProcCall-Statm");

        ProcCallStatm pc = new ProcCallStatm(s.curLineNum());
        s.test(nameToken);
        pc.name = s.curToken.id;
        s.skip(nameToken);

        if(s.curToken.kind == leftParToken){
            s.skip(leftParToken);

            while(true){
                pc.expr = Expression.parse(s);

                if(s.curToken.kind != commaToken){
                    break;
                }
            }
            s.skip(rightParToken);
        }

        leaveParser("ProcCall-Statm");
        return pc;
    }
}
