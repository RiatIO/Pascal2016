package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

class Expression extends PascalSyntax {
    SimpleExpr se;
    RelOperator ro;

    Expression(int lNum) {
        super(lNum);
    }

    @Override public String identify() {
        return "<expression> on line " + lineNum;
    }

    static Expression parse(Scanner s) {
        enterParser("expression");
        

        leaveParser("expression");
        return b;
    }
}
