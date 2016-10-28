package parser;

import main.*;
import scanner.*;
import java.util.ArrayList;
import static scanner.TokenKind.*;

class Constant extends PascalSyntax {

    PrefixOperator po;
    UnsignedConstant uc;

    types.Type type;
    int constVal;

    Constant(int lNum) {
        super(lNum);
    }

    @Override public String identify() {
        return "<Constant> on line " + lineNum;
    }

    @Override public void prettyPrint() {
        if (po != null) {
            po.prettyPrint();
        }

        uc.prettyPrint();
    }

    @Override void check(Block curScope, Library lib) {
        uConst.check(curScope, lib);
        type = uConst.type;
        constVal = uConst.constVal;

        if (prefix != null) {
            String oprName = prefix.opr.kind.toString();
            uConst.type.checkType(lib.integerType, "Prefix "+oprName, this,
                "Prefix + or - may only be applied to Integers.");
            if (prefix.opr.kind == subtractToken)
                constVal = -constVal;
        }
    }

    static Constant parse(Scanner s) {
        enterParser("constant");

        Constant c = new Constant(s.curLineNum());

        if (s.curToken.kind.isPrefixOpr()) {
            c.po = PrefixOperator.parse(s);
        }

        c.uc = UnsignedConstant.parse(s);

        leaveParser("constant");
        return c;
    }
}
