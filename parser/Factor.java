package parser;


import main.*;
import scanner.*;
import static scanner.TokenKind.*;

public abstract class Factor extends PascalSyntax {

    types.Type type;

    Factor(int lNum) {
        super(lNum);
    }

    @Override public String identify() {
        return "<factor> on line " + lineNum;
    }

    static Factor parse(Scanner s) {
        enterParser("factor");

        Factor fo = null;

        switch (s.curToken.kind) {
            case nameToken:
                switch(s.nextToken.kind) {
                    case leftBracketToken:
                        fo = Variable.parse(s); break;
                    case leftParToken:
                        fo = FuncCall.parse(s); break;
                    default:
                        fo = Variable.parse(s); break;
                }
                break;
            case leftParToken:
                fo = InnerExpr.parse(s); break;
            case notToken:
                fo = Negation.parse(s); break;
            default:
                fo = UnsignedConstant.parse(s); break;
        }

        leaveParser("factor");
        return fo;
    }
}
