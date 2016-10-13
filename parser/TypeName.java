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
        Main.log.prettyPrint(name);
    }

    static TypeName parse(Scanner s) {
        enterParser("type name");
        TypeName tn = new TypeName(s.curLineNum());

        s.test(nameToken);
        tn.name = s.curToken.id;
        s.readNextToken();

        leaveParser("type name");
        return tn;
    }
}
