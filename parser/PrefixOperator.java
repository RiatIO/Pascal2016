package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

/* <program> ::= ’program’ <name> ’:’ <block> '.' */

class PrefixOperator extends Operator {

    public TokenKind tk;

    PrefixOperator(int lNum) {
        super(lNum);
    }

    @Override void check(Block curScope, Library lib) {
        type = lib.intType;
    }

    @Override public String identify() {
        return "<prefixoperator> on line " + lineNum;
    }

    @Override public void prettyPrint() {
        Main.log.prettyPrint(" " + tk.toString() + " ");
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
