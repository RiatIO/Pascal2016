package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

public class Library extends Block {

    public Library(int lNum) {
        super(lNum);
    }

    @Override public String identify() {
        return "<library> on line " + lineNum;
    }

    @Override public void prettyPrint() {

    }

    static Library parse(Scanner s) {
        enterParser("library");


        leaveParser("library");
        return null;
    }
}
