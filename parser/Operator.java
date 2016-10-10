package parser;

public abstract class Operator extends PascalSyntax {

    Operator(int lNum) {
        super(lNum);
    }

    @Override public String identify() {
        return "<operator> on line " + lineNum;
    }

    @Override public void prettyPrint() {

    }

    // static Operator parse(Scanner s) {
    //     enterParser("operator");
    //
    //     leaveParser("operator");
    //     return op;
    // }
}
