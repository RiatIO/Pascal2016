package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

public abstract class Operator extends Factor {

    Operator(int lNum) {
        super(lNum);
    }

    @Override public String identify() {
        return "<operator> on line " + lineNum;
    }

    @Override public void prettyPrint() {

    }
}
