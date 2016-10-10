package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

class ProcCallStatm extends Statement{
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


        leaveParser("ProcCall-Statm");
        return null;
    }
}
