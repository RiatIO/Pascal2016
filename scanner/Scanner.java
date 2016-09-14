package scanner;

import main.Main;
import static scanner.TokenKind.*;

import java.io.*;

public class Scanner {
    public Token curToken = null, nextToken = null;

    private LineNumberReader sourceFile = null;
    private String sourceFileName, sourceLine = "";
    private int sourcePos = 0;

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
        curToken = nextToken;
        nextToken = null;

        while (nextToken == null) {

            if (sourceLine.length() == 1) {
                System.out.println("    DEBUG: Empty line found");
                readNextLine();
            } /* if sourceLine's length is 1, read next line */

            if (sourcePos >= sourceLine.length() - 1) {
                readNextLine();
            } /* End of line reached */

            // checkAndRemoveComment();

            for (int i = sourcePos; i < sourceLine.length(); i++) {
                char curr = sourceLine.charAt(i);
                sourcePos++;
                System.out.format("CURR: %c - len: %d - pos: %d\n", curr, sourceLine.length(), sourcePos);

                if (isLetterAZ(curr) || isDigit(curr)) {
                    buffer.append(curr);
                } /* is current char a letter or digit, append to buffer */
                else if ((curr == ' ' || curr == '\t') && buffer.length() == 0) {
                    continue;
                } /* if current char is empty or is a tab, and buffer is empty, continue */
                else if (curr == ' ') {
                    String token = buffer.toString().toLowerCase();
                    nextToken    = insert(token, getFileLineNum());

                    buffer.setLength(0);
                    break;
                } /* current char is empty and buffer isnt. Make a token. */
                else if (curr == '\'') {
                    if (sourceLine.charAt(i + 2) != '\'') {
                        error("Illegal char literal!");
                    }

                    nextToken = insert(sourceLine.charAt(i + 1), getFileLineNum());
                    sourcePos+=2;

                    if (sourceLine.charAt(i + 3) == '\'') {
                        sourcePos++;
                    } /* Ugly case: escape char for ' (i.e write('''') <-- valid) */

                    break;
                }
                else if (!buffer.toString().isEmpty()) {
                    String token = buffer.toString().toLowerCase();
                    nextToken    = insert(token, getFileLineNum());

                    sourcePos--;
                    buffer.setLength(0);
                    break;
                }
                else if (curr == '{' || (sourceLine.charAt(i) == '/' && sourceLine.charAt(i+1) == '*')) {
                    removeComments(curr == '{' ? "}" : "*/");
                    break;
                }
                else {
                    String temp = String.valueOf(curr);
                    char next   = sourceLine.charAt(i + 1);

                    if (!isDigit(next) && !isLetterAZ(next) && next != ' ' && next != '\'' && next != '\t') {
                        temp += String.valueOf(next);
                        sourcePos++;
                    }

                    boolean isTokenFound = false;
                    int iterate = temp.length();

                    for (int x = 0; x < iterate; x++) {
                        for (TokenKind k : TokenKind.values()) {
                            if (k.toString().equals(temp)) {
                                nextToken    = insert(k, getFileLineNum());
                                isTokenFound = true;
                                break;
                            }
                        }
                        if (!isTokenFound) {
                            temp = String.valueOf(temp.charAt(0));
                            sourcePos--;
                        }
                    } /* Only loop twice, as thats max. seq. of chars to check for*/

                    if (nextToken == null) {
                        error(String.format("Illegal character: '%s'!", temp));
                    } /* If nexttoken is null the inputed sign is illegal*/
                    break;
                }
            }

            if (sourceLine.isEmpty()) {
                System.out.println("    DEBUG: End of File");
                nextToken = insert(eofToken, getFileLineNum());
            } /* Check for end-of-file */
        }

        if (nextToken != null) {
            System.out.println(nextToken.identify());
        }

        Main.log.noteToken(nextToken);
    }

    public Token insert(Object d, int len) {
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

            if (token.length() > 1 && isDigit(token.charAt(0)) && isLetterAZ(token.charAt(1))) {
                Main.error("    FAIL: ");
            }
            else if (isDigit(token.charAt(0))) {
                return new Token(Integer.parseInt((String)token), getFileLineNum());
            }

            return new Token(token, len);
        }

        return null;
    }

    private void readNextLine() {
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
        if (sourceFile != null)
            Main.log.noteSourceLine(getFileLineNum(), sourceLine);
    }

    private void removeComments(String endToken){
        boolean isEndTokenFound = false;

        for(; sourceFile != null; sourcePos++){
            if (endToken == "}" && sourceLine.charAt(sourcePos) == '}') {
                isEndTokenFound = true;
                sourcePos++;
                break;
            }
            else if (sourceLine.charAt(sourcePos) == '*' && sourceLine.charAt(sourcePos+1) == '/') {
                isEndTokenFound = true;
                sourcePos += 2;
                break;
            }
            else if(sourcePos == sourceLine.length()-1){
                readNextLine();
                sourcePos--;
            }
        }

        if (!isEndTokenFound)
            error("No end for comment");
    }

    public void checkAndRemoveComment() {
        String s = sourceLine.indexOf("/*") > -1 ? "*/" : "}";

        if (sourceLine.indexOf("/*") > -1 || sourceLine.indexOf("{") > -1) {

            boolean isMultiline = false;
            while (sourceLine.indexOf(s) == -1) {
                isMultiline = true;
                readNextLine();

                if (sourceLine.isEmpty()) {
                    error("No end for comment");
                }
            }

            StringBuilder temp = new StringBuilder(sourceLine);

            int end = sourceLine.indexOf(s);

            if (isMultiline) {
                temp.delete(0, end + s.length());
            } else {
                int start = (s.equals("*/") ? sourceLine.indexOf("/*") : sourceLine.indexOf("{"));
                temp.delete(start, end + s.length());
            }
            sourceLine = temp.toString();
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
}
