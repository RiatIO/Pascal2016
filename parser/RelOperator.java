package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

/* <program> ::= ’program’ <name> ’:’ <block> '.' */

class RelOperator extends Operator {

    RelOperator(int lNum) {
        super(lNum);
    }

    @Override public String identify() {
        return "<RelOperator> on line " + lineNum;
    }

    static RelOperator parse(Scanner s) {
        enterParser("RelOperator");

        leaveParser("RelOperator");
        return null;
    }
}
