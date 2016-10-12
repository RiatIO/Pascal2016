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
        enterParser("rel opr");

        RelOperator ro = new RelOperator(s.curLineNum());

        switch (s.curToken.kind) {
            case equalToken:
                ro.tk = multiplyToken; break;
            case notEqualToken:
                ro.tk = notEqualToken; break;
            case lessToken:
                ro.tk = lessToken; break;
            case lessEqualToken:
                ro.tk = lessEqualToken; break;
            case greaterToken:
                ro.tk = greaterToken; break;
            case greaterEqualToken:
                ro.tk = greaterEqualToken; break;
        }

        s.readNextToken();

        leaveParser("rel opr");
        return ro;
    }
}
