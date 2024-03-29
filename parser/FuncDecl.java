package parser;

import main.*;

import scanner.*;
import java.util.ArrayList;
import static scanner.TokenKind.*;

class FuncDecl extends ProcDecl {
    Block b;
    TypeName tn;

    String label;

    FuncDecl(String id, int lNum) {
        super(id, lNum);
    }

    @Override void genCode(CodeFile f) {
        int size = b.vdp == null ? 32 : b.vdp.size;
        label = f.getLabel(name);

        if (!b.pd.isEmpty()) {
            int level = declLevel + 1;

            for (ProcDecl a : b.pd) {
                a.declLevel = level;
                a.genCode(f);
            }
        }

        f.genInstr("func$" + label, "enter", String.format("$%d,$%d", size,
                                                        declLevel), "Start of " + name);

        if (!b.pd.isEmpty()) {
            for (ProcDecl a : b.pd) {
                a.genCode(f);
            }
        }

        b.genCode(f);

        f.genInstr("", "movl", "-32(%ebp),%eax", "Fetch return value");
        f.genInstr("", "leave", "", "End of " + name);
        f.genInstr("", "ret", "", "");
	}

	@Override void check(Block curScope, Library lib) {
        curScope.addDecl(name, this);

        if (pdl != null) {
            b.outerScope = curScope;
            b.blockId = curScope.blockId + 1;
            declLevel = b.blockId;

            // b.blockId = (b.levelCount + 1); // Ugly but must be done.
            // declLevel = b.blockId + 1;
            pdl.check(b, lib);
        }

        tn.check(curScope, lib);
        type = tn.type;

        b.check(curScope, lib);
        declLevel = b.blockId;
    }

    @Override public String identify() {
        return "<func decl> "+ name + " on line " + lineNum;
    }

    @Override public void prettyPrint() {
        Main.log.prettyPrintLn();
        Main.log.prettyPrint("function " + name);

        if (pdl != null) {
            Main.log.prettyPrint(" ");
            pdl.prettyPrint();
        }

        Main.log.prettyPrint(": ");
        tn.prettyPrint();

        Main.log.prettyPrintLn(";");

        b.prettyPrint();

        Main.log.prettyPrintLn("; {" + name + "}");
        // Main.log.prettyPrintLn();
    }

    static FuncDecl parse(Scanner s) {
        enterParser("func decl");
        s.skip(functionToken);
        s.test(nameToken);

        FuncDecl fd = new FuncDecl(s.curToken.id, s.curLineNum());

        s.readNextToken();

        if (s.curToken.kind != colonToken) {
            fd.pdl = ParamDeclList.parse(s);
        }

        s.skip(colonToken);
        fd.tn = TypeName.parse(s);
        s.skip(semicolonToken);

        fd.b = Block.parse(s);
        s.skip(semicolonToken);

        leaveParser("func decl");
        return fd;
    }

    @Override void checkWhetherAssignable(PascalSyntax where){
    }
    @Override void checkWhetherFunction(PascalSyntax where){
    }
    @Override void checkWhetherProcedure(PascalSyntax where){
        where.error(name + " is a function, not a procedure.");
    }
    @Override void checkWhetherValue(PascalSyntax where){
    }
}
