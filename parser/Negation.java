package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

class Negation extends Factor {

    Factor fc;
    types.Type type;

    Negation(int lNum) {
        super(lNum);
    }

    @Override void check(Block curScope, Library lib) {
    }

    @Override public String identify() {
        return "<negation> on line " + lineNum;
    }

    @Override public void prettyPrint() {
        Main.log.prettyPrint("not ");
        fc.prettyPrint();
    }

    static Negation parse(Scanner s) {
        enterParser("negation");

        s.skip(notToken);

        Negation n = new Negation(s.curLineNum());

        n.fc = Factor.parse(s);

        leaveParser("negation");
        return n;
    }
}
