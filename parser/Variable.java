package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

class Variable extends Factor {

    Expression expr;
    String name;

    PascalDecl varDecl;

    Variable(int lNum) {
        super(lNum);
    }

    @Override void genCode(CodeFile f) {
        if (expr != null) {
            types.ArrayType temp = ((types.ArrayType) varDecl.type);

            expr.genCode(f);

            if (temp.loLim != 0)
                f.genInstr("", "subl", "$" + temp.loLim + ",%eax", "");

            f.genInstr("", "movl", -4 * varDecl.declLevel + "(%ebp),%edx", "");
            f.genInstr("", "leal", -1 * varDecl.declOffset + "(%edx),%edx", "");
            f.genInstr("", "movl", "0(%edx,%eax,4),%eax", "  " + varDecl.name + "[...]"); // DAG HAR DU FEIL??? (0)

        } else if (varDecl instanceof ConstDecl) {
            varDecl.genCode(f);
        } else if (varDecl instanceof VarDecl) {
            f.genInstr("", "movl", (-4 * varDecl.declLevel) + "(%ebp),%edx", "");
            f.genInstr("", "movl", -1 * varDecl.declOffset + "(%edx),%eax", "  " + varDecl.name);
        } else {
            // Param Decl
            f.genInstr("", "movl", (-4 * varDecl.declLevel) + "(%ebp),%edx", "");
            f.genInstr("", "movl", varDecl.declOffset + "(%edx),%eax", "  " + varDecl.name);
        }
	}

	@Override void check(Block curScope, Library lib) {
        varDecl = curScope.findDecl(name, this);
        varDecl.checkWhetherValue(this);
        type = varDecl.type;

        if (expr != null) {
            expr.check(curScope, lib);
            types.ArrayType temp = ((types.ArrayType) varDecl.type);
            type = temp.elemType;
            expr.type.checkType(temp.indexType, "array index", this, "array index do not match!");
        }
    }

    @Override public String identify() {
        return "<variable> on line " + lineNum;
    }

    @Override public void prettyPrint() {
        Main.log.prettyPrint(name);

        if (expr != null) {
            Main.log.prettyPrint("[");
            expr.prettyPrint();
            Main.log.prettyPrint("]");
        }
    }

    static Variable parse(Scanner s) {
        enterParser("variable");

        Variable v = new Variable(s.curLineNum());

        s.test(nameToken);
        v.name = s.curToken.id;
        s.readNextToken();

        if (s.curToken.kind == leftBracketToken) {
            s.skip(leftBracketToken);

            v.expr = Expression.parse(s);

            s.skip(rightBracketToken);
        }

        leaveParser("variable");
        return v;
    }
}
