package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

class EmptyStatm extends Statement{
    EmptyStatm(String id, int lNum){
        super(id,lNum);
    }

    @Override public String identify() {
        return "<Empty-statm> " + name + "on line " + lineNum;
    }

    static EmptyStatm parse(Scanner s){
        enterParser("Empty-Statm");


        leaveParser("Empty-Statm");
        return 0;
    }
}
