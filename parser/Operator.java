package parser;

public abstract class Operator extends PascalSyntax {

    static Operator parse(Scanner s) {
        enterParser("operator");
        
        leaveParser("operator");
        return op;
    }
}
