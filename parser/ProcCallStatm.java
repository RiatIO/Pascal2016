package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;
import java.util.ArrayList;

class ProcCallStatm extends Statement {
    ArrayList<Expression> expr;
    String name;

    ProcDecl ref;

    ProcCallStatm(int lNum){
        super(lNum);
        expr = new ArrayList<>();
    }

    @Override void genCode(CodeFile f) {

        for (Expression e : expr) {
            e.genCode(f);
            f.genInstr("", "pushl", "%eax", "Push next param.");
            if (name.equals("write")) {
                f.genInstr("", "call", "write_" + e.type.identify(), "");
                f.genInstr("", "addl", "$4,%esp", "Pop param.");
            }
        }

        if (!name.equals("write")) {
            f.genInstr("", "call", "proc$" + ref.label, "");
            f.genInstr("", "addl", "$8,%esp", "");
        }
	}

	@Override void check(Block curScope, Library lib) {
        ref = (ProcDecl) curScope.findDecl(name, this);
        ref.checkWhetherProcedure(this);

        int pos = 0;

        if (!expr.isEmpty()) {
            for (Expression e : expr) {
                e.check(curScope, lib);
                type = e.type;

                if (ref.pdl != null) {
                    if (ref.pdl.pd.size() < pos + 1)
                        this.error("Too many parameters in call on " + name);

                    if (ref.pdl.pd.size() != expr.size())
                        this.error("Too few parameters in call on " + name);

                    type.checkType(ref.pdl.pd.get(pos).type, "param #" + (pos+1), this, "Illegal type of parameter #" + (pos+1));
                    pos++;
                }
            }
        }
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
