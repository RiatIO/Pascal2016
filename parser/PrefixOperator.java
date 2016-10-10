package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

/* <program> ::= ’program’ <name> ’:’ <block> '.' */

class PrefixOperator extends Operator {

    PrefixOperator(int lNum) {
        super(lNum);
    }

    @Override public String identify() {
        return "<prefixoperator> on line " + lineNum;
    }

    static PrefixOperator parse(Scanner s) {
        enterParser("prefixoperator");

        leaveParser("prefixoperator");
        return null;
    }
}
