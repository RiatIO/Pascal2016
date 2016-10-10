package parser;

import main.*;
import scanner.*;
import java.util.ArrayList;
import static scanner.TokenKind.*;

class Constant extends PascalSyntax {

    PrefixOperator po;
    UnsignedConstant uc;

    Constant(int lNum) {
        super(lNum);
    }

    @Override public String identify() {
        return "<Constant> on line " + lineNum;
    }

    @Override public void prettyPrint() {

    }

    static Constant parse(Scanner s) {
        enterParser("Constant");

        Constant c = new Constant(s.curLineNum());

        if (s.curToken.kind.isPrefixOpr()) {
            c.po = PrefixOperator.parse(s);
        }

        c.uc = UnsignedConstant.parse(s);

        leaveParser("Constant");
        return c;
    }
}
