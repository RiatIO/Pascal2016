package types;

public class BoolType extends Type {
    @Override public String identify() {
        return "bool";
    }

    @Override public int size() {
        return 4;
    }
}
