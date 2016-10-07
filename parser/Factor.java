package parser;

public abstract class Factor extends PascalSyntax {

    Factor(int lNum) {
        super(lNum);
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
