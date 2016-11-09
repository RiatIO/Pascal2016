package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

public abstract class UnsignedConstant extends Factor {

    int constVal;

    UnsignedConstant(int lNum) {
        super(lNum);
    }

    @Override void genCode(CodeFile f) {
        f.genInstr("", "movl", "$" + constVal + ",%eax", "");
        f.genInstr("", "pushl", "%eax", "");
        f.genInstr("", "call", "write_" + type.identify(), "");
        f.genInstr("", "addl", "$4,%esp", "");
    }

    static UnsignedConstant parse(Scanner s) {
        enterParser("unsigned constant");

        UnsignedConstant fo = null;

        switch (s.curToken.kind) {
            case nameToken:
                fo = NamedConst.parse(s); break;
            case intValToken:
                fo = NumberLiteral.parse(s); break;
            case charValToken:
                fo = CharLiteral.parse(s); break;
            default:
                fo = CharLiteral.parse(s); break;
        }

        leaveParser("unsigned constant");
        return fo;
    }
}
