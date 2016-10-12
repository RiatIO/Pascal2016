package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

/* <program> ::= ’program’ <name> ’:’ <block> '.' */

class PrefixOperator extends Operator {

    TokenKind tk;

    PrefixOperator(int lNum) {
        super(lNum);
    }

    @Override public String identify() {
        return "<prefixoperator> on line " + lineNum;
    }

    static PrefixOperator parse(Scanner s) {
        enterParser("prefix opr");

        PrefixOperator po = new PrefixOperator(s.curLineNum());

        if (s.curToken.kind == addToken) {
            po.tk = addToken;
        } else {
            po.tk = subtractToken;
        }

        s.skip(s.curToken.kind);

        leaveParser("prefix opr");
        return po;
    }
}
