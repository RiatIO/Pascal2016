package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

/* <program> ::= ’program’ <name> ’:’ <block> '.' */

class FactorOperator extends Operator {
    TokenKind k;

    FactorOperator(int lNum) {
        super(lNum);
    }

    @Override public String identify() {
        return "<factor-operator> " + name + " on line " + lineNum;
    }

    static FactorOperator parse(Scanner s) {
        enterParser("factor-operator");

        FactorOperator fo = new FactorOperator(s.curLineNum());

        switch (s.curToken.kind) {
            case multiplyToken:
                fo.k = multiplyToken;
            case divToken:
                fo.k = divToken;
            case modToken:
                fo.k = modToken;
            case andToken:
                fo.k = andToken;
        }

        leaveParser("factor-operator");
        return fo;
    }
}
