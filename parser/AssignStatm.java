package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

class AssignStatm extends Statement{
    AssignStatm(int lNum){
        super(lNum);
    }

    @Override public String identify() {
        return "<Assign-statm> on line " + lineNum;
    }

    @Override public void prettyPrint() {

    }

    static AssignStatm parse(Scanner s){
        enterParser("Assign-Statm");


        leaveParser("Assign-Statm");
        return null;
    }
}
