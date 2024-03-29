package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;
import java.util.ArrayList;

class FuncCall extends Factor {

    ArrayList<Expression> expr;
    String name;

    FuncDecl ref;

    FuncCall(int lNum) {
        super(lNum);
        expr = new ArrayList<>();
    }

    @Override void genCode(CodeFile f) {
        for (int i = expr.size() - 1; i >= 0; i--) {
            expr.get(i).genCode(f);
            f.genInstr("", "pushl", "%eax", "Push param #" + (i+1));
        }

        f.genInstr("", "call", "func$" + ref.label, "");

        if (!expr.isEmpty())
            f.genInstr("", "addl", "$" + (expr.size()) * 4 + ",%esp", "Pop parameters");
	}

	@Override void check(Block curScope, Library lib) {
        PascalDecl d = curScope.findDecl(name, this);
        ref = (FuncDecl) d;
        ref.checkWhetherFunction(this);

        int pos = 0;
        for (Expression e : expr) {
            e.check(curScope, lib);
            type = e.type;

            if (ref.pdl != null) {
                type.checkType(ref.type, "param #" + (pos+1), this, "Left operand to + is not a number!");
                pos++;
            }
        }
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
