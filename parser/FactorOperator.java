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
        return "<factor-operator> on line " + lineNum;
    }

    @Override public void prettyPrint() {
        Main.log.prettyPrint(k.toString());
    }

    static FactorOperator parse(Scanner s) {
        enterParser("factor opr");

        FactorOperator fo = new FactorOperator(s.curLineNum());

        switch (s.curToken.kind) {
            case multiplyToken:
                fo.k = multiplyToken; break;
            case divToken:
                fo.k = divToken; break;
            case modToken:
                fo.k = modToken; break;
            case andToken:
                fo.k = andToken; break;
        }

        s.readNextToken();

        leaveParser("factor opr");
        return fo;
    }
}
