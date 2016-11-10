package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

class CharLiteral extends UnsignedConstant {

    char c;

    CharLiteral(int lNum) {
        super(lNum);
    }

    @Override void genCode(CodeFile f) {
        f.genInstr("", "movl", "$" + constVal + ",%eax", String.format("    '%c'", c));
    }

	@Override void check(Block curScope, Library lib) {
        constVal = (int)c;
        type = lib.charType;
    }

    @Override public String identify() {
        return "<charliteral> on line " + lineNum;
    }

    @Override public void prettyPrint() {
        Main.log.prettyPrint("'" + (c =='\'' ? "''" : c) + "'");
    }

    static CharLiteral parse(Scanner s) {
        enterParser("char literal");

        CharLiteral cl = new CharLiteral(s.curLineNum());

        s.test(charValToken);
        cl.c = s.curToken.charVal;
        s.skip(s.curToken.kind);

        leaveParser("char literal");
        return cl;
    }
}
