package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

public abstract class UnsignedConstant extends Factor {

    UnsignedConstant(int lNum) {
        super(lNum);
    }

    static UnsignedConstant parse(Scanner s) {
        enterParser("unsignedconstant");

        UnsignedConstant fo = null;

        leaveParser("unsignedconstant");
        return fo;
    }
}
