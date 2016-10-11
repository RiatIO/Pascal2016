package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;
import java.util.ArrayList;

class TypeName extends Type {
    String name;

    TypeName(int lNum) {
        super(lNum);
    }

    @Override public String identify() {
        return "<TypeName> on line " + lineNum;
    }

    @Override public void prettyPrint() {

    }

    static TypeName parse(Scanner s) {
        enterParser("name type");
        TypeName tn = new TypeName(s.curLineNum());

        s.test(nameToken);
        tn.name = s.curToken.id;
        s.readNextToken();

        leaveParser("name type");
        return tn;
    }
}
