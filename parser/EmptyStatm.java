package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

class EmptyStatm extends Statement {

    types.Type type;

    EmptyStatm(int lNum){
        super(lNum);
    }

    @Override void genCode(CodeFile f) {

	}

	@Override void check(Block curScope, Library lib) {
    }

    @Override public String identify() {
        return "<Empty-statm> on line " + lineNum;
    }

    @Override public void prettyPrint() {

    }

    static EmptyStatm parse(Scanner s){
        enterParser("empty statm");

        EmptyStatm es = new EmptyStatm(s.curLineNum());

        leaveParser("empty statm");
        return es;
    }
}
