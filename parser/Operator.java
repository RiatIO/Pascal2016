package parser;

public abstract class Operator extends PascalSyntax {

    Operator(int lNum) {
        super(lNum);
    }
    
    static Operator parse(Scanner s) {
        enterParser("operator");
        Operator op = null;

        switch (s.curToken.kind) {
            case equalToken:
                op =


            switch (s.nextToken.kind) {
                case assignToken:
                    st = AssignStatm.parse(s); break;
                case leftBracketToken:
                    st = AssignStatm.parse(s);  break;
                default:
                    st = ProcCallStatm.parse(s);  break;
            } break;

            case whileToken:
                st = WhileStatm.parse(s);  break;
            default:
                st = EmptyStatm.parse(s);  break;
        }
        leaveParser("operator");
        return op;
    }
}
