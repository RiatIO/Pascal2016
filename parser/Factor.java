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
                if (s.nextToken.kind == leftParToken)
                    fo = FuncCall.parse(s);
                else if (s.nextToken.kind == leftBracketToken)
                    fo = Variable.parse(s);
                else
                    fo = UnsignedConstant.parse(s); // <-- IS THIS RIGHT?
                break;
            case leftParToken:
                fo = InnerExpr.parse(s); break;
            case notToken:
                fo = Negation.parse(s); break;
            default:
                fo = UnsignedConstant.parse(s); break;
        }

        System.out.println("HAHAHAH");
        System.out.println(fo);

        leaveParser("factor");
        return fo;
    }
}
