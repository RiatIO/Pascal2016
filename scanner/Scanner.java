package scanner;

import main.Main;
import static scanner.TokenKind.*;

import java.io.*;

public class Scanner {
    public Token curToken = null, nextToken = null;

    private LineNumberReader sourceFile = null;
    private String sourceFileName, sourceLine = "";
    private int sourcePos = 0;

    // Statistics
    private int tokensCreated = 0, charsIterated = 0, linesRead = 0, commentsIgn = 0;

    // Create a token builder.
    private StringBuilder buffer = new StringBuilder();

    public Scanner(String fileName) {
        sourceFileName = fileName;
        try {
            sourceFile = new LineNumberReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            Main.error("Cannot read " + fileName + "!");
        }

        readNextToken(); readNextToken();
    }

    public String identify() {
        return "Scanner reading " + sourceFileName;
    }

    public int curLineNum() {
        return curToken.lineNum;
    }

    private void error(String message) {
        Main.error("Scanner error on " +
        (curLineNum()<0 ? "last line" : "line "+curLineNum()) +
        ": " + message);
    }

    public void readNextToken() {
        curToken  = nextToken;
        nextToken = null;

        while (nextToken == null) {
            if (sourceLine.length() == 1 || sourcePos >= sourceLine.length() - 1) {
                readNextLine();
            } /* if sourceLine's length is 1 or end of line reached */

            for (int i = sourcePos; i < sourceLine.length(); i++) {
                char curr = sourceLine.charAt(i);
                sourcePos++; charsIterated++; // Increment for statistics

                if (isLetterAZ(curr) || isDigit(curr)) {
                    buffer.append(curr);
                } /* is current char a letter or digit, append to buffer */
                else if ((curr == ' ' || curr == '\t') && buffer.length() == 0) {
                    continue;
                } /* if current char is empty or is a tab, and buffer is empty, continue */
                else if (curr == ' ' || curr == '\t') {
                    String token = buffer.toString().toLowerCase();
                    nextToken    = insert(token, getFileLineNum());
                    break;
                } /* current char is empty and buffer isnt. Make a token. */
                else if (curr == '\'') {
                    if (sourceLine.charAt(i + 2) != '\'') {
                        error("Illegal char literal!");
                    } /* Check if literal has an ending */

                    nextToken  = insert(sourceLine.charAt(i + 1), getFileLineNum());
                    sourcePos += 2;

                    if (sourceLine.charAt(i + 3) == '\'') {
                        sourcePos++;
                    } /* Ugly case: escape char for ' (i.e write('''') <-- valid) */
                    break;
                }  /* Check if current is an apostrof, and handle it correctly */
                else if (!buffer.toString().isEmpty()) {
                    String token = buffer.toString().toLowerCase();
                    nextToken    = insert(token, getFileLineNum());
                    sourcePos--;
                    break;
                } /* If buffer is not empty, and curr char is a special char */
                else if (curr == '{' || (sourceLine.charAt(i) == '/' && sourceLine.charAt(i+1) == '*')) {
                    removeComments(curr == '{' ? "}" : "*/");
                    break;
                } /* If comment, handle it accordingly */
                else {
                    String temp = String.valueOf(curr);
                    char next   = sourceLine.charAt(i + 1);

                    checkForSpecialChar(temp, next);

                    if (nextToken == null) {
                        error(String.format("Illegal character: '%s'!", temp));
                    } /* If nexttoken is null the inputed sign is illegal*/
                    break;
                } /* Finally, the char potentially be a special char. */
            }

            if (sourceLine.isEmpty()) {
                nextToken = insert(eofToken, getFileLineNum());
                printStatus();
            } /* Check for end-of-file */
        }

        tokensCreated++;    // Increment for statistics
        Main.log.noteToken(nextToken);
    }

    /**
     * Method used to find corresponding token kind for given char.
     * It'll loop throught the enums and check if the enum value
     * is equal to current char, and if so, return a new token.
     *
     * @param temp current char converted to string.
     * @param next next char. token.
     */
    private void checkForSpecialChar(String temp, char next) {
        if (!isDigit(next) && !isLetterAZ(next) && next != ' ' && next != '\'' && next != '\t') {
            temp += String.valueOf(next);
            sourcePos++;
        } /* Check whether next char is potentially a special char. */

        boolean isTokenFound = false;
        int iterate = temp.length();

        for (int i = 0; i < iterate; i++) {
            for (TokenKind k : TokenKind.values()) {
                if (k.toString().equals(temp)) {
                    nextToken    = insert(k, getFileLineNum());
                    isTokenFound = true;
                    break;
                } /* If tokendkind found, insert */
            } /* Loop the enums */
            if (!isTokenFound) {
                temp = String.valueOf(temp.charAt(0));
                sourcePos--;
            } /* Tokenkind of two special chars not found, try with one instead */
        } /* Only loop twice, as thats max. seq. of chars to check for */
    }

    /**
     * Might be a redundent method at the moment, but might come in handy
     * in the future.
     *
     * @param d an arbitrary Object
     * @param len line of found token.
     */
    private Token insert(Object d, int len) {

        if (d instanceof TokenKind) {
            return new Token((TokenKind)d, len);
        }

        if (d instanceof Integer) {
            return new Token((int)d, len);
        }

        if (d instanceof Character) {
            return new Token((char)d, len);
        }

        if (d instanceof String) {
            String token = (String)d;
            buffer.setLength(0);    // Reset the global buffer.

            if (token.matches("[0-9]+")) {
                return new Token(Integer.parseInt((String)token), getFileLineNum());
            }

            return new Token(token, len);
        }

        return null;
    }

    /**
     * Loop until end-of-comment is found and ignore everything in it
     *
     * @param endOfCommentToken what end-of-comment char to check for
     */
    private void removeComments(String endOfCommentToken){
        commentsIgn++;  // Increment for statistics

        boolean isEndTokenFound = false;

        for (; sourceFile != null; sourcePos++) {
            if (endOfCommentToken == "}" && sourceLine.charAt(sourcePos) == '}') {
                isEndTokenFound = true;
                sourcePos++;
                break;
            }
            else if (endOfCommentToken == "*/" && (sourceLine.charAt(sourcePos) == '*' && sourceLine.charAt(sourcePos+1) == '/')) {
                isEndTokenFound = true;
                sourcePos += 2;
                break;
            }
            else if (sourcePos >= sourceLine.length()-1) {
                readNextLine();
                sourcePos--;
            }
        }

        if (!isEndTokenFound)
            error("No end for comment");
    }

    private void readNextLine() {
        linesRead++;    // Increment for statistics

        if (sourceFile != null) {
            try {
                sourceLine = sourceFile.readLine();
                if (sourceLine == null) {
                    sourceFile.close();
                    sourceFile = null;
                    sourceLine = "";
                } else {
                    sourceLine += " ";
                }
                sourcePos = 0;
            } catch (IOException e) {
                Main.error("Scanner error: unspecified I/O error!");
            }
        }
        if (sourceFile != null) {
            Main.log.noteSourceLine(getFileLineNum(), sourceLine);
        }
    }

    private int getFileLineNum() {
        return (sourceFile!=null ? sourceFile.getLineNumber() : 0);
    }

    // Character test utilities:
    private boolean isLetterAZ(char c) {
        return 'A'<=c && c<='Z' || 'a'<=c && c<='z';
    }

    private boolean isDigit(char c) {
        return '0'<=c && c<='9';
    }

    // Parser tests:
    public void test(TokenKind t) {
        if (curToken.kind != t)
            testError(t.toString());
    }

    public void testError(String message) {
        Main.error(curLineNum(),
        "Expected a " + message +
        " but found a " + curToken.kind + "!");
    }

    public void skip(TokenKind t) {
        test(t);
        readNextToken();
    }

    public void printStatus() {
        System.out.printf("-------------%s-------------\n", sourceFileName);
        System.out.printf("Status: read and tokenized\n\nTokens:   %d found\nChars:    %d iterated\nLines:    %d read\nComments: %d ignored\n",
                        tokensCreated, charsIterated, linesRead, commentsIgn);
        System.out.println("----------------------------------------");
    }
}
