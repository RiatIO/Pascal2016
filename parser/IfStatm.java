package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

class IfStatm extends Statement{
    Expression expr;
    Statement body;

    IfStatm(int lNum){
        super(lNum);
    }

    @Override public String identify() {
        return "<If-statm> on line " + lineNum;
    }

    @Override public void prettyPrint() {

    }

    static IfStatm parse(Scanner s){
        enterParser("if-Statm");

        IfStatm ifs = new IfStatm(s.curLineNum());
        s.skip(ifToken);

        ifs.expr = Expression.parse(s);
        s.skip(thenToken);

        ifs.body = Statement.parse(s);
        s.skip(elseToken);

        ifs.body = Statement.parse(s);

        leaveParser("If-Statm");
        return ifs;
    }
}
