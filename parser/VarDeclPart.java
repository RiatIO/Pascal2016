package parser;

import main.*;
import scanner.*;
import java.util.ArrayList;
import static scanner.TokenKind.*;

class VarDeclPart extends PascalSyntax {

    VarDeclPart(int lNum) {
        super(lNum);
    }

    @Override public String identify() {
        return "<VarDeclPart> on line " + lineNum;
    }

    @Override public void prettyPrint() {

    }

    static ConstDeclPart parse(Scanner s) {
        enterParser("VarDeclPart");

        leaveParser("VarDeclPart");
        return null;
    }
}
