package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;
import java.util.ArrayList;

class ProcCallStatm extends Statement{
    ArrayList<Expression> expr;
    String name;
    PascalDecl procRef;

    types.Type type;

    ProcCallStatm(int lNum){
        super(lNum);
        expr = new ArrayList<>();
    }

    @Override void check(Block curScope, Library lib) {
        PascalDecl d = curScope.findDecl(name, this);

        if (!expr.isEmpty()) {
            for (Expression e : expr) {
                e.check(curScope, lib);
            }
        }

        procRef = (ProcDecl) d;
    }

    @Override public String identify() {
        return "<ProcCall-statm> on line " + lineNum;
    }

    @Override public void prettyPrint() {
        Main.log.prettyPrint(name);

        Main.log.prettyPrint("(");
        if (!expr.isEmpty()) {

            for (int i = 0; i < expr.size(); i++) {
                if (i != 0) {
                    Main.log.prettyPrint(", ");
                }
                expr.get(i).prettyPrint();
            }

        }
        Main.log.prettyPrint(")");
    }

    static ProcCallStatm parse(Scanner s){
        enterParser("proc call");

        ProcCallStatm pc = new ProcCallStatm(s.curLineNum());
        s.test(nameToken);
        pc.name = s.curToken.id;
        s.skip(nameToken);

        if (s.curToken.kind == leftParToken) {
            s.skip(leftParToken);

            while (true){
                pc.expr.add(Expression.parse(s));

                if (s.curToken.kind != commaToken) {
                    break;
                }

                s.skip(commaToken);
            }
            s.skip(rightParToken);
        }

        leaveParser("proc call");
        return pc;
    }
}
