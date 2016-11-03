package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;
import java.util.ArrayList;

class FuncCall extends Factor {

    ArrayList<Expression> expr;
    String name;

    PascalDecl funcRef;

    FuncCall(int lNum) {
        super(lNum);
        expr = new ArrayList<>();
    }

    @Override void check(Block curScope, Library lib) {
        PascalDecl d = curScope.findDecl(name, this);
        type = d.type;

        int i = 1;
        for (Expression e : expr) {
            e.check(curScope, lib);
            type.checkType(e.type, "param #" + i, this, "mofo");
            i++;
        }
        
        funcRef = (FuncDecl) d;
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
        enterParser("func call");

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

        leaveParser("func call");
        return fc;
    }
}
