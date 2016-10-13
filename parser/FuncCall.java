package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;
import java.util.ArrayList;

class FuncCall extends Factor {

    ArrayList<Expression> expr;
    String name;

    FuncCall(int lNum) {
        super(lNum);
        expr = new ArrayList<>();
    }

    @Override public String identify() {
        return "<funcCall> on line " + lineNum;
    }

    @Override public void prettyPrint() {
        Main.log.prettyPrint(name);

        if (!expr.isEmpty()) {
            Main.log.prettyPrint("(");
            for (int i = 0; i < expr.size(); i++) {
                if (i != 0) {
                    Main.log.prettyPrint(", ");
                }
                expr.get(i).prettyPrint();
            }
            Main.log.prettyPrint(")");
        }
    }

    static FuncCall parse(Scanner s) {
<<<<<<< HEAD
        enterParser("funcCall");
=======
        enterParser("func call");
>>>>>>> 2383d82e46b7d71a889a587a4f9cafabbf009a13

        FuncCall fc = new FuncCall(s.curLineNum());

        s.test(nameToken);
        fc.name = s.curToken.id;
        s.skip(nameToken);

        if (s.curToken.kind == leftParToken) {
            s.skip(leftParToken);

            while (true) {
                fc.expr.add(Expression.parse(s));

                if (s.curToken.kind == commaToken) {
                    s.skip(commaToken);
                } else {
                    break;
                }
            }

            s.skip(rightParToken);
        }

<<<<<<< HEAD
        leaveParser("funcCall");
=======
        leaveParser("func call");
>>>>>>> 2383d82e46b7d71a889a587a4f9cafabbf009a13
        return fc;
    }
}
