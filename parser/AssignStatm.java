package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

class AssignStatm extends Statement{
    Variable v;
    Expression e;

    AssignStatm(int lNum){
        super(lNum);
    }

    @Override public String identify() {
        return "<Assign-statm> on line " + lineNum;
    }

    @Override public void prettyPrint() {

    }

    static AssignStatm parse(Scanner s){
        enterParser("Assign-Statm");

        AssignStatm asgn = new AssignStatm(s.curLineNum);
        asgn.v = Variable.parse(s);
        s.skip(assignToken);
        asgn.e = Expression.parse(s); 

        leaveParser("Assign-Statm");
        return null;
    }
}
