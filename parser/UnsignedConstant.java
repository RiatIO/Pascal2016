package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

public abstract class UnsignedConstant extends Factor {

    UnsignedConstant(int lNum) {
        super(lNum);
    }

    static UnsignedConstant parse(Scanner s) {
        enterParser("unsignedconstant");

        UnsignedConstant fo = null;

        switch (s.curToken.kind) {
            case nameToken:
                fo = NamedConst.parse(s);
                break;
            case intValToken:
                fo = NumberLiteral.parse(s);
                break;
            case charValToken:
                fo = CharLiteral.parse(s);
                break;
        }

        leaveParser("unsignedconstant");
        return fo;
    }
}

abstract class Statement extends PascalSyntax {
    Statement(int lNum) {
        super(lNum);
    }
    static Statement parse(Scanner s) {
        enterParser("statement");
        Statement st = null;

        switch (s.curToken.kind) {
            case beginToken:
                st = CompoundStatm.parse(s);  break;
            case ifToken:
                st = IfStatm.parse(s);  break;
            case nameToken:
                switch (s.nextToken.kind) {
                    case assignToken:
                        st = AssignStatm.parse(s); break;
                    case leftBracketToken:
                        st = AssignStatm.parse(s);  break;
                    default:
                        st = ProcCallStatm.parse(s);  break;
                }
                break;
            case whileToken:
                st = WhileStatm.parse(s);  break;
            default:
                st = EmptyStatm.parse(s);  break;
        }
        leaveParser("statement");
        return st;
    }
}
