package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

class Variable extends Factor {

    Expression expr;
    String name;

    PascalDecl varDecl;

    Variable(int lNum) {
        super(lNum);
    }

    @Override void check(Block curScope, Library lib) {
        if (expr != null)
            expr.check(curScope, lib);

        PascalDecl d = curScope.findDecl(name, this);

        if (d instanceof VarDecl)
            varDecl = (VarDecl) d;
        else if (d instanceof ConstDecl)
            varDecl = (ConstDecl) d;
        else if (d instanceof ParamDecl)
            varDecl = (ParamDecl) d;
    }

    @Override public String identify() {
        return "<variable> on line " + lineNum;
    }

    @Override public void prettyPrint() {
        Main.log.prettyPrint(name);

        if (expr != null) {
            Main.log.prettyPrint("[");
            expr.prettyPrint();
            Main.log.prettyPrint("]");
        }
    }

    static Variable parse(Scanner s) {
        enterParser("variable");

        Variable v = new Variable(s.curLineNum());

        s.test(nameToken);
        v.name = s.curToken.id;
        s.readNextToken();

        if (s.curToken.kind == leftBracketToken) {
            s.skip(leftBracketToken);

            v.expr = Expression.parse(s);

            s.skip(rightBracketToken);
        }

        leaveParser("variable");
        return v;
    }
}
