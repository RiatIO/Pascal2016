package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;
import java.util.ArrayList;
import java.util.HashMap;

class Block extends PascalSyntax {
    static int levelCount;
    int blockId;

    Program context;
    Block outerScope;
    Library lib;

    ConstDeclPart cdp;
    VarDeclPart vdp;
    StatmList sm;

    ArrayList<ProcDecl> pd;
    HashMap<String, PascalDecl> decls;

    Block(int lNum) {
        super(lNum);
        pd = new ArrayList<>();
        decls = new HashMap<>();
    }

    @Override void genCode(CodeFile f) {
        if (cdp != null) {
            cdp.genCode(f);
        }

        if (vdp != null) {
            vdp.genCode(f);
        }

        if (!pd.isEmpty()) {
            for (ProcDecl a : pd) {
                a.genCode(f);
            }
        }

        if (sm != null) {
            sm.genCode(f);
        }
	}

	@Override void check(Block curScope, Library lib) {
        this.outerScope = curScope;
        this.lib = lib;
        this.blockId = ++levelCount;
        Main.debug(lineNum, "Block", "Level: " + this.blockId);

        if (cdp != null) {
            cdp.check(this, lib);
        }

        if (vdp != null) {
            vdp.check(this, lib);
        }

        if (!pd.isEmpty()) {
            for (ProcDecl a : pd) {
                a.check(this, lib);
            }
        }

        if (sm != null) {
            sm.check(this, lib);
        }
    }

    void addDecl(String id, PascalDecl d) {
        if (decls.containsKey(id))
            d.error(id + " declared twice in same block!");
        decls.put(id, d);
    }

    @Override public String identify() {
        return "<block> on line " + lineNum;
    }

    public PascalDecl findDecl(String id, PascalSyntax where) {
        PascalDecl d = decls.get(id);

        if (d != null) {
            Main.log.noteBinding(id, where, d);
            return d;
        }

        if (outerScope != null) {
            return outerScope.findDecl(id, where);
        }

        where.error("Name " + id + " is unknown!");
        return null; // Required by the Java compiler.
    }

    @Override public void prettyPrint() {
        if (cdp != null)
            cdp.prettyPrint();
        if (vdp != null)
            vdp.prettyPrint();

        if (!pd.isEmpty()) {
            for (ProcDecl p : pd) {
                p.prettyPrint();
            }
            Main.log.prettyPrintLn();
        }
        Main.log.prettyPrint("begin");
        Main.log.prettyPrintLn();

        sm.prettyPrint();
        Main.log.prettyPrint("end");
    }

    static Block parse(Scanner s) {
        enterParser("block");

        Block b = new Block(s.curLineNum());

        if (s.curToken.kind == constToken) {
            b.cdp = ConstDeclPart.parse(s);
        }

        if (s.curToken.kind == varToken) {
            b.vdp = VarDeclPart.parse(s);
        }

        while(true) {
            if (s.curToken.kind == functionToken) {
                b.pd.add(FuncDecl.parse(s));
            } else if (s.curToken.kind == procedureToken) {
                b.pd.add(ProcDecl.parse(s));
            } else {
                break;
            }
        }

        s.skip(beginToken);

        b.sm = StatmList.parse(s);

        s.skip(endToken);

        leaveParser("block");
        return b;
    }
}
