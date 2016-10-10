package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

class AssignStatm extends Statement{
    AssignStatm(String id, int lNum){
        super(id,lNum);
    }

    @Override public String identify() {
        return "<Assign-statm> " + name + "on line " + lineNum;
    }

    static AssignStatm parse(Scanner s){
        enterParser("Assign-Statm");


        leaveParser("Assign-Statm");
        return 0
    }
}
