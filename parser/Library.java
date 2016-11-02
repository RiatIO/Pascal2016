package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

public class Library extends Block {
    types.BoolType booleanType;
    types.IntType integerType;

    public Library(int lNum) {
        super(lNum);

        addDecl("true",     new ConstDecl("true", 0));
        addDecl("false",    new ConstDecl("false", 0));
        addDecl("eol",      new ConstDecl("eol", 0));
        addDecl("write",    new ProcDecl("write", 0));
        addDecl("array",    new ProcDecl("array", 0));
        addDecl("boolean",  new TypeDecl("boolean", 0));
        addDecl("char",     new TypeDecl("char", 0));
        addDecl("integer",  new TypeDecl("integer", 0));
    }

    @Override public String identify() {
        return "<library> on line " + lineNum;
    }
}
