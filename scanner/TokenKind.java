package scanner;

enum Type {
    SPECIAL
}

// Note that tokens found in standard Pascal but not in Pascal2016
// have been commented out.

public enum TokenKind {
    nameToken("name"),
    intValToken("number"),
    charValToken("char"),

    addToken("+", Type.SPECIAL),
    assignToken(":=", Type.SPECIAL),
    colonToken(":", Type.SPECIAL),
    commaToken(",", Type.SPECIAL),
    /* divideToken("/"), */
    dotToken(".", Type.SPECIAL),
    equalToken("=", Type.SPECIAL),
    greaterToken(">", Type.SPECIAL),
    greaterEqualToken(">=", Type.SPECIAL),
    leftBracketToken("[", Type.SPECIAL),
    leftParToken("(", Type.SPECIAL),
    lessToken("<", Type.SPECIAL),
    lessEqualToken("<=", Type.SPECIAL),
    multiplyToken("*", Type.SPECIAL),
    notEqualToken("<>", Type.SPECIAL),
    rangeToken("..", Type.SPECIAL),
    rightBracketToken("]", Type.SPECIAL),
    rightParToken(")", Type.SPECIAL),
    semicolonToken(";", Type.SPECIAL),
    subtractToken("-", Type.SPECIAL),
    /* upArrowToken("^"), */

    andToken("and"),
    arrayToken("array"),
    beginToken("begin"),
    /* caseToken("case"), */
    constToken("const"),
    divToken("div"),
    doToken("do"),
    /* downtoToken("downto"), */
    elseToken("else"),
    endToken("end"),
    /* fileToken("file"), */
    /* forToken("for"), */
    functionToken("function"),
    /* gotoToken("goto"), */
    ifToken("if"),
    /* inToken("in"), */
    /* labelToken("label"), */
    modToken("mod"),
    /* nilToken("nil"), */
    notToken("not"),
    ofToken("of"),
    orToken("or"),
    /* packedToken("packed"), */
    procedureToken("procedure"),
    programToken("program"),
    /* recordToken("record"), */
    /* repeatToken("repeat"), */
    /* setToken("set"), */
    thenToken("then"),
    /* toToken("to"), */
    /* typeToken("type"), */
    /* untilToken("until"), */
    varToken("var"),
    whileToken("while"),
    /* withToken("with"), */

    eofToken("e-o-f");

    private String image;
    private Type type;

    TokenKind(String im) {
        image = im;
    }

    TokenKind(String im, Type k) {
        image = im;
        type  = k;
    }

    public String identify() {
        return image + " token";
    }

    public Type getType() {
        return type;
    }

    @Override public String toString() {
        return image;
    }

    public boolean isFactorOpr() {
        return this==multiplyToken || this==divToken ||
        this==modToken || this==andToken;
    }

    public boolean isPrefixOpr() {
        return this==addToken || this==subtractToken;
    }

    public boolean isRelOpr() {
        return this==equalToken || this==notEqualToken ||
        this==lessToken || this==lessEqualToken ||
        this==greaterToken || this==greaterEqualToken;
    }

    public boolean isTermOpr() {
        return isPrefixOpr() || this==orToken;
    }
}
