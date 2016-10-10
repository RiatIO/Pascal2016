package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

/* <program> ::= ’program’ <name> ’:’ <block> '.' */

class RelOperator extends Operator {

    TokenKind tk;

    RelOperator(int lNum) {
        super(lNum);
    }

    @Override public String identify() {
        return "<RelOperator> on line " + lineNum;
    }

    static RelOperator parse(Scanner s) {
        enterParser("RelOperator");

        RelOperator ro = new RelOperator(s.curLineNum());

        switch (s.curToken.kind) {
            case equalToken:
                fo.k = multiplyToken; break;
            case notEqualToken:
                fo.k = notEqualToken; break;
            case lessToken:
                fo.k = lessToken; break;
            case lessEqualToken:
                fo.k = lessEqualToken; break;
            case greaterToken:
                fo.k = greaterToken; break;
            case greaterEqualToken:
                fo.k = greaterEqualToken; break;
        }

        s.readNextToken();

        leaveParser("RelOperator");
        return null;
    }
}
