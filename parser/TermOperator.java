package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

/* <program> ::= ’program’ <name> ’:’ <block> '.' */

class TermOperator extends Operator {

    TermOperator(int lNum) {
        super(lNum);
    }

    @Override public String identify() {
        return "<TermOperator> on line " + lineNum;
    }

    static TermOperator parse(Scanner s) {
        enterParser("TermOperator");

        leaveParser("TermOperator");
        return null;
    }
}
