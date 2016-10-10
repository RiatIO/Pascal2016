package parser;


import main.*;
import scanner.*;
import static scanner.TokenKind.*;

public abstract class Factor extends PascalSyntax {

    Factor(int lNum) {
        super(lNum);
    }

    @Override public String identify() {
        return "<factor> on line " + lineNum;
    }

    @Override public void prettyPrint() {

    }

    static Factor parse(Scanner s) {
        enterParser("factor");

        Factor fo = null;

        switch (s.curToken.kind) {
            case nameToken:
                fo = FuncCall.parse(s);
                // fo = Variable.parse(s); <-- WHAAA
            case leftParToken:
                fo = InnerExpr.parse(s);
            case notToken:
                fo = Negation.parse(s);
        }

        leaveParser("factor");
        return fo;
    }
}
