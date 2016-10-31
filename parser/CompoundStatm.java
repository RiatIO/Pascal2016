package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

class CompoundStatm extends Statement{
    StatmList sl;

    types.Type type;

    CompoundStatm(int lNum){
        super(lNum);
    }

    @Override void check(Block curScope, Library lib) {
    }

    @Override public String identify() {
        return "<Compound-statm> on line " + lineNum;
    }

    @Override public void prettyPrint() {
        Main.log.prettyPrintLn("begin");

        sl.prettyPrint();

        Main.log.prettyPrint("end");
    }

    static CompoundStatm parse(Scanner s){
        enterParser("compound statm");
        CompoundStatm cp = new CompoundStatm(s.curLineNum());
        s.skip(beginToken);
        cp.sl = StatmList.parse(s);
        s.skip(endToken);

        leaveParser("compound statm");
        return cp;
    }
}
