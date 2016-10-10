package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

class CompoundStatm extends Statement{
    CompoundStatm(int lNum){
        super(lNum);
    }

    @Override public String identify() {
        return "<Compound-statm> on line " + lineNum;
    }

    @Override public void prettyPrint() {

    }

    static CompoundStatm parse(Scanner s){
        enterParser("CompoundStatm");


        leaveParser("CompoundStatm");
        return null;
    }
}
