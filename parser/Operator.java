package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

public abstract class Operator extends Factor {

    types.Type type;

    Operator(int lNum) {
        super(lNum);
    }

    @Override void check(Block curScope, Library lib) {
    }

    @Override public String identify() {
        return "<operator> on line " + lineNum;
    }

    @Override public void prettyPrint() {

    }
}
