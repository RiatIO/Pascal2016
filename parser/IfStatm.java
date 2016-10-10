package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

class IfStatm extends Statement{
    IfStatm(String id, int lNum){
        super(id,lNum);
    }

    @Override public String identify() {
        return "<If-statm> " + name + "on line " + lineNum;
    }

    static IfStatm parse(Scanner s){
        enterParser("if-Statm");


        leaveParser("If-Statm");
        return 0
    }
}
