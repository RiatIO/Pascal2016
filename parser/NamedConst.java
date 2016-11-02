package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

class NamedConst extends UnsignedConstant {

    String name;

    PascalDecl ref;

    NamedConst(int lNum) {
        super(lNum);
    }

    // FIX THIS
    @Override void check(Block curScope, Library lib) {
        PascalDecl d = curScope.findDecl(name, this);
        System.out.println(" IN NAME CONST: " + d);
        ref = (ConstDecl) d;
        type = ref.type;
        System.out.println(ref.type);
        // constVal = ((ConstDecl) d). <-- FIX THIS
    }

    @Override public String identify() {
        return "<namedconst> on line " + lineNum;
    }

    @Override public void prettyPrint() {
        Main.log.prettyPrint(name);
    }

    static NamedConst parse(Scanner s) {
        enterParser("named constant");

        NamedConst nc = new NamedConst(s.curLineNum());

        s.test(nameToken);
        nc.name = s.curToken.id;
        s.skip(s.curToken.kind);

        leaveParser("named constant");
        return nc;
    }
}
