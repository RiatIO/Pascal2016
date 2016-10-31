package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

public class Library extends Block {
    types.Type type;

    public Library(int lNum) {
        super(lNum);
    }

    // add dec
    // get dec
    // find dec

    @Override public String identify() {
        return "<library> on line " + lineNum;
    }
}
