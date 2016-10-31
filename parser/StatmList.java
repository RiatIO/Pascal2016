package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;
import java.util.ArrayList;

class StatmList extends PascalSyntax {
    ArrayList<Statement> s;

    types.Type type;

    StatmList(int lNum) {
        super(lNum);
        s = new ArrayList<>();
    }

    @Override void check(Block curScope, Library lib) {
    }

    @Override public String identify() {
        return "<statmlist> on line " + lineNum;
    }

    @Override public void prettyPrint() {
        Main.log.prettyIndent();

        for (int i = 0; i < s.size(); i++) {
            if (i != 0) {
                Main.log.prettyPrint(";");
                Main.log.prettyPrintLn();
            }
            s.get(i).prettyPrint();
        }

        Main.log.prettyPrintLn();
        Main.log.prettyOutdent();
    }

    static StatmList parse(Scanner s) {
        enterParser("statm list");

        StatmList sl = new StatmList(s.curLineNum());

        while (true) {
            sl.s.add(Statement.parse(s));

            if (s.curToken.kind == semicolonToken) {
                s.skip(semicolonToken);
            } else {
                break;
            }
        }

        leaveParser("statm list");
        return sl;
    }
}
