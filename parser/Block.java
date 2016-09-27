package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

class Block extends PascalSyntax {

    Block(int lNum) {
        super(lNum);
    }

    @Override public String identify() {
        return "<block> on line " + lineNum;
    }

    static Block parse(Scanner s) {
        enterParser("block")

        


        leaveParser("block");
        return b;
    }

}
