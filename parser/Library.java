package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

public class Library extends Block {
    types.BoolType booleanType;
    types.IntType integerType;

    public Library(int lNum) {
        super(lNum);

        addDecl("write", new ProcDecl("write", lNum));
        addDecl("integer", new ProcDecl("integer", lNum));
    }

    @Override public String identify() {
        return "<library> on line " + lineNum;
    }
}
