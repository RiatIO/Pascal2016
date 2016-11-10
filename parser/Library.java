package parser;

import main.*;
import scanner.*;

public class Library extends Block {
    types.BoolType boolType = new types.BoolType();
    types.IntType intType   = new types.IntType();
    types.CharType charType = new types.CharType();

    public Library(int lNum) {
        super(lNum);
        ConstDecl _true, _false, _eol;
        TypeDecl _boolean, _char, _int;

        _true = new ConstDecl("true", 0);
        _true.type  = boolType;
        _true.cst   = new Constant(lNum);
        _true.cst.constVal = 1;

        _false = new ConstDecl("false", 0);
        _false.type = boolType;
        _false.cst  = new Constant(lNum);
        _false.cst.constVal = 0;

        _eol = new ConstDecl("eol", 0);
        _eol.type   = charType;
        _eol.cst    = new Constant(lNum);
        _eol.cst.constVal = 10;

        _boolean = new TypeDecl("boolean", 0);
        _boolean.type = boolType;

        _char = new TypeDecl("char", 0);
        _char.type = charType;

        _int = new TypeDecl("integer", 0);
        _int.type = intType;

        addDecl("true",     _true);
        addDecl("false",    _false);
        addDecl("eol",      _eol);
        addDecl("write",    new ProcDecl("write", 0));
        addDecl("boolean",  _boolean);
        addDecl("char",     _char);
        addDecl("integer",  _int);
    }

    @Override public void genCode(CodeFile f) {

    }

    @Override public String identify() {
        return "<library> on line " + lineNum;
    }
}
