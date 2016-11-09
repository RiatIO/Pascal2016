package types;

public class CharType extends Type {
    @Override public String identify() {
        return "char";
    }

    @Override public int size() {
        return 4;
    }
}
