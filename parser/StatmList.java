package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;
import java.util.ArrayList;

class StatmList extends PascalSyntax {
    ArrayList<Statement> s;

    StatmList(int lNum) {
        super(lNum);
        s = new ArrayList<>();
    }

    @Override public String identify() {
        return "<statmlist> on line " + lineNum;
    }

    @Override public void prettyPrint() {

    }

    static StatmList parse(Scanner s) {
        enterParser("statm list");

        StatmList sl = new StatmList(s.curLineNum());

        while (true) {
            sl.s.add(Statement.parse(s));

            try {
                s.skip(semicolonToken);
            } catch (PascalError e) {
                break;
            }
        }

        leaveParser("statm list");
        return sl;
    }
}
