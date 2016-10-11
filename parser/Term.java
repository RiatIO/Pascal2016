package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;
import java.util.ArrayList;

class Term extends PascalSyntax {
    ArrayList<Statement> s;

    Term(int lNum) {
        super(lNum);
    }

    @Override public String identify() {
        return "<statmlist> on line " + lineNum;
    }

    @Override public void prettyPrint() {

    }

    static Term parse(Scanner s) {
        enterParser("term");
        
        leaveParser("term");
        return null;
    }
}
