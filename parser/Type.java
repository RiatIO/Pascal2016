ackage parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

abstract class Type extends PascalSyntax {
    Statement(int lNum) {
        super(lNum);
    }
    static Type parse(Scanner s) {

        }
        leaveParser("statement");
        return st;
    }
}
