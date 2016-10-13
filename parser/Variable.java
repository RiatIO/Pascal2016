package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

class Variable extends Factor {

    Expression expr;
    String name;

    Variable(int lNum) {
        super(lNum);
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
