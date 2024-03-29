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

    @Override void genCode(CodeFile f) {

	}

	@Override void check(Block curScope, Library lib) {
        type = lib.boolType;
    }

    @Override public String identify() {
        return "<RelOperator> on line " + lineNum;
    }

    @Override public void prettyPrint() {
        Main.log.prettyPrint(tk.toString());
    }

    static RelOperator parse(Scanner s) {
        enterParser("rel opr");

        RelOperator ro = new RelOperator(s.curLineNum());

        switch (s.curToken.kind) {
            case equalToken:
                ro.tk = equalToken; break;
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
