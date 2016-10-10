package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

class EmptyStatm extends Statement{
    EmptyStatm(int lNum){
        super(lNum);
    }

    @Override public String identify() {
        return "<Empty-statm> on line " + lineNum;
    }

    @Override public void prettyPrint() {

    }

    static EmptyStatm parse(Scanner s){
        enterParser("Empty-Statm");

        s.skip(semicolonToken);
        
        leaveParser("Empty-Statm");
        return null;
    }
}
