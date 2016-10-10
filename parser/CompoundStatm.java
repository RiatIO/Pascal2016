package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

class CompoundStatm extends Statement{
    CompoundStatm(String id, int lNum){
        super(id,lNum);
    }

    @Override public String identify() {
        return "<Compound-statm> " + name + "on line " + lineNum;
    }

    static CompoundStatm parse(Scanner s){
        enterParser("Assing-Statm");


        leaveParser("Assign-Statm");
        return 0;
    }
}
