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

    // Pretty print here
    static VarDeclPart parse(Scanner s) {
        enterParser("VarDeclPart");

        leaveParser("VarDeclPart");
        return null;
    }
}
