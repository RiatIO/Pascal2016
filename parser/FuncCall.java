package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

class FuncCall extends Factor {

    Expression expr;
    String name;

    FuncCall(int lNum) {
        super(lNum);
    }

    @Override public String identify() {
        return "<funccall> on line " + lineNum;
    }

    static FuncCall parse(Scanner s) {
        enterParser("funccall");

        FuncCall fc = new FuncCall(s.curLineNum());

        s.test(nameToken);
        fc.name = s.curToken.id;
        s.skip(nameToken);

        if (s.curToken.kind == leftParToken) {
            s.skip(leftParToken);

            while (true) {
                fc.expr = Expression.parse(s);

                if (s.curToken.kind != commaToken)
                    break;
            }
            s.skip(rightParToken);
        }

        leaveParser("funccall");
        return fc;
    }
}
