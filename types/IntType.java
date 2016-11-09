package types;

public class IntType extends Type {
    @Override public String identify() {
        return "int";
    }

    @Override public int size() {
        return 4;
    }
}
