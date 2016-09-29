package parser;

import main.*;

import scanner.*;
import static scanner.TokenKind.*;


abstract class Statement extends PascalSyntax {
    Statement(int lNum) {
        super(lNum);
    }
    static Statement parse(Scanner s) {
        enterParser("statement");
        Statement st = null;

        switch (s.curToken.kind) {
            case whileToken:
                st = WhileStatm.parse(s);  break;
            default:
                //st = EmptyStatm.parse(s);  break;
        }
        leaveParser("statement");
        return st;
    }
}
