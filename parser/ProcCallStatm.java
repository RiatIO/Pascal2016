package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

class ProcCallStatm extends Statement{
    ProcCallStatm(String id, int lNum){
        super(id,lNum);
    }

    @Override public String identify() {
        return "<ProcCall-statm> " + name + "on line " + lineNum;
    }

    static ProcCall parse(Scanner s){
        enterParser("ProcCall-Statm");


        leaveParser("ProcCall-Statm");
        return 0;
    }
}
