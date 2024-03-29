package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

/* <program> ::= ’program’ <name> ’:’ <block> '.' */

class TermOperator extends Operator {

    TokenKind tk;

    TermOperator(int lNum) {
        super(lNum);
    }

    @Override void genCode(CodeFile f) {

	}

	@Override void check(Block curScope, Library lib) {
        type = (tk == orToken) ? lib.boolType : lib.intType;
    }

    @Override public String identify() {
        return "<TermOperator> on line " + lineNum;
    }

    @Override public void prettyPrint() {
        Main.log.prettyPrint(tk.toString());
    }

    static TermOperator parse(Scanner s) {
        enterParser("term opr");

        TermOperator to = new TermOperator(s.curLineNum());

        if (s.curToken.kind == addToken) {
            to.tk = addToken;
        } else if (s.curToken.kind == subtractToken) {
            to.tk = subtractToken;
        } else {
            to.tk = orToken;
        }

        s.skip(s.curToken.kind);

        leaveParser("term opr");
        return to;
    }
}
