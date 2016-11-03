package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

class AssignStatm extends Statement{
    Variable v;
    Expression e;

    types.Type type;

    AssignStatm(int lNum){
        super(lNum);
    }

    @Override void check(Block curScope, Library lib) {
        v.check(curScope, lib);
        e.check(curScope, lib);
        System.out.println("Var" + v.type + " - " + " expr " + e.type);
        v.type.checkType(e.type, ":=", this, "FAILED");
    }

    @Override public String identify() {
        return "<Assign-statm> on line " + lineNum;
    }

    @Override public void prettyPrint() {
        v.prettyPrint();
        Main.log.prettyPrint(" := ");
        e.prettyPrint();
    }

    static AssignStatm parse(Scanner s){
        enterParser("assign statm");

        AssignStatm asgn = new AssignStatm(s.curLineNum());
        asgn.v = Variable.parse(s);
        s.skip(assignToken);
        asgn.e = Expression.parse(s);

        leaveParser("assign statm");
        return asgn;
    }
}
