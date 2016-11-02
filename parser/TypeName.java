package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;
import java.util.ArrayList;

class TypeName extends Type {
    String name;

    PascalDecl procRef;

    TypeName(int lNum) {
        super(lNum);
    }

    @Override void check(Block curScope, Library lib) {
        PascalDecl d = curScope.findDecl(name, this);

        if (d.lineNum == 0)
            procRef = (TypeDecl) d;
        else
            procRef = (ProcDecl) d;

    }

    @Override public String identify() {
        return "<TypeName> on line " + lineNum;
    }

    @Override public void prettyPrint() {
        Main.log.prettyPrint(name);
    }

    public String toString() {
        return name;
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
