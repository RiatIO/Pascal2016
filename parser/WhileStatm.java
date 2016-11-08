package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

/* <while-statm> ::= ’while’ <expression> ’do’ <statement> */

class WhileStatm extends Statement {
    Expression expr;
    Statement body;

    WhileStatm(int lNum) {
        super(lNum);
    }

    @Override void genCode(CodeFile f) {

	}

	@Override void check(Block curScope, Library lib) {
        expr.check(curScope, lib);
        expr.type.checkType(lib.boolType, "while-test", this,
            "While-test is not Boolean.");
        body.check(curScope, lib);
    }

    @Override public String identify() {
        return "<while-statm> on line " + lineNum;
    }

    @Override void prettyPrint() {
        Main.log.prettyPrint("while "); expr.prettyPrint();
        Main.log.prettyPrintLn(" do"); Main.log.prettyIndent();
        body.prettyPrint(); Main.log.prettyOutdent();
    }

    static WhileStatm parse(Scanner s) {
        enterParser("while-statm");

        WhileStatm ws = new WhileStatm(s.curLineNum());
        s.skip(whileToken);

        ws.expr = Expression.parse(s);
        s.skip(doToken);
        ws.body = Statement.parse(s);

        leaveParser("while-statm");
        return ws;
    }
}
