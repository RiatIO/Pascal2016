package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

abstract class Type extends PascalSyntax {

    types.Type type;

    Type(int lNum) {
        super(lNum);
    }

    @Override void check(Block curScope, Library lib) {
    }

    @Override public String identify() {
        return "<type> on line " + lineNum;
    }

    public void checkType(Type tx, String op, PascalSyntax where, String mess) {
        Main.log.noteTypeCheck(this, op, tx, where);
        if (this != tx)
            where.error(mess);
    }

    static Type parse(Scanner s) {
        enterParser("type");
        Type t = null;

        if (s.curToken.kind == nameToken) {
            t = TypeName.parse(s);
        } else {
            t = ArrayType.parse(s);
        }

        leaveParser("type");
        return t;
    }
}
