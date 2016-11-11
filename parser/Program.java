package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

/* <program> ::= ’program’ <name> ’:’ <block> '.' */

public class Program extends PascalDecl {
    Block progBlock;

    Program(String id, int lNum) {
        super(id, lNum);
    }

    @Override public void genCode(CodeFile f) {
        String startLabel = "prog$" + f.getLabel(name);
        int size = progBlock.vdp == null ? 32 : progBlock.vdp.size;

        f.genInstr("", ".globl main", "", "");
        f.genInstr("main", "call", startLabel, "Start program");
        f.genInstr("", "movl", "$0,%eax", "Set status 0 and");
        f.genInstr("", "ret", "", "terminate the program");

        if (!progBlock.pd.isEmpty()) {
            for (ProcDecl a : progBlock.pd) {
                a.genCode(f);
            }
        }

        System.out.println("LEVEL " + declLevel);

        f.genInstr(startLabel, "enter", String.format("$%d,$%d", size,
                                                progBlock.blockId), "Start of " + name);
        progBlock.genCode(f);
        f.genInstr("", "leave", "", "End of " + name);
        f.genInstr("", "ret", "", "");
	}

    @Override public void check(Block curScope, Library lib) {
        curScope.addDecl(name, this);
        progBlock.check(curScope, lib);
        declLevel = progBlock.blockId;
    }

    @Override public String identify() {
        return "<program> " + name + " on line " + lineNum;
    }

    @Override public void prettyPrint() {
        Main.log.prettyPrint("program " + name + ";");
        Main.log.prettyPrintLn();
        progBlock.prettyPrint();
        Main.log.prettyPrint(".");
    }

    public static Program parse(Scanner s) {
        enterParser("program");
        s.skip(programToken);
        s.test(nameToken);

        Program p = new Program(s.curToken.id, s.curLineNum());
        s.readNextToken();
        s.skip(semicolonToken);

        p.progBlock = Block.parse(s); p.progBlock.context = p;
        s.skip(dotToken);

        leaveParser("program");
        return p;
    }

    void checkWhetherAssignable(PascalSyntax where){

    }
    void checkWhetherFunction(PascalSyntax where){

    }
    void checkWhetherProcedure(PascalSyntax where){

    }
    void checkWhetherValue(PascalSyntax where){

    }
}
