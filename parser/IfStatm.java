package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

class IfStatm extends Statement{
    IfStatm(int lNum){
        super(lNum);
    }

    @Override public String identify() {
        return "<If-statm> on line " + lineNum;
    }

    @Override public void prettyPrint() {

    }

    static IfStatm parse(Scanner s){
        enterParser("if-Statm");


        leaveParser("If-Statm");
        return null;
    }
}
