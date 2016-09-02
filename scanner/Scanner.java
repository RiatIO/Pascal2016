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
            readNextLine(); // Set the reading pointer to point to first line
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
        // Del 1 her:

        if (sourcePos >= sourceLine.length() - 1) {
            System.out.println(String.format("INFO: %d(%d) ", sourcePos, sourceLine.length()-1));
            System.out.println("    DEBUG: reading new line ");
            readNextLine();
        } /* End of line reached */

        while (sourceLine.length() == 1) {
            System.out.println("    DEBUG: Empty line found");
            readNextLine();
        } /* If soureline is empty, continue the till */

        // Check current sourceLine for comments and remove if so
        checkAndRemoveComments();

        for (int i = sourcePos; i < sourceLine.length(); i++) {
            //System.out.println("Current line: " + sourceLine + " LINE " +sourceLine.length());
            char curr = sourceLine.charAt(i);
            sourcePos++;

            System.out.format("curr: %c - pos: %d - len: %d\n", curr, sourcePos, sourceLine.length());

            if (isLetterAZ(curr) || isDigit(curr)) {
                buffer.append(curr);
            }
            else if (curr == ' ' && buffer.length() == 0) {
                continue;
            }
            else if (curr == ' ') {
                String token = buffer.toString().toLowerCase();

                nextToken = new Token(token, getFileLineNum());

                if (token.length() > 1 && isDigit(token.charAt(0)) && isLetterAZ(token.charAt(1))) {
                    Main.error("FAIL");
                }else if (isDigit(token.charAt(0))) {
                    nextToken = new Token(Integer.parseInt(token), getFileLineNum());
                }

                System.out.format("STRING Scanner: '%s'\n", token);
                //System.out.println("Scanner: " + nextToken.identify());
                buffer.setLength(0);
                break;
            }
            else if (curr == '\'') {
                nextToken = new Token(sourceLine.charAt(i + 1), getFileLineNum());

                i+=2;
                sourcePos+=2;
                break;
            }
            else {
                if (!buffer.toString().isEmpty()) {
                    String token = buffer.toString().toLowerCase();
                    nextToken    = new Token(token, getFileLineNum());

                    if (token.length() > 1 && isDigit(token.charAt(0)) && isLetterAZ(token.charAt(1))) {
                        Main.error("FAIL");
                    }
                    else if (isDigit(token.charAt(0))) {
                        nextToken = new Token(Integer.parseInt(token), getFileLineNum());
                    }

                    System.out.format("CHAR Scanner: '%s'\n", token);

                    buffer.setLength(0);
                    sourcePos--;
                }
                else {
                    String temp = String.valueOf(curr);
                    char next   = sourceLine.charAt(i + 1);

                    nextToken = new Token(temp, getFileLineNum());
                    System.out.format("IM HERE Scanner: '%c'\n", curr);

                    boolean isTokenFound = false;
                    if (!isDigit(next) && !isLetterAZ(next) && next != ' ' && next != '\'') {
                        temp += String.valueOf(next);
                        sourcePos++;
                    }

                    System.out.println(temp);
                    while (!isTokenFound) {
                        for (TokenKind k : TokenKind.values()) {
                            if (k.toString().equals(temp)) {
                                nextToken = new Token(k, getFileLineNum());
                                isTokenFound = true;
                            }
                        }
                        if (!isTokenFound) {
                            temp = String.valueOf(temp.charAt(0));
                        }
                    }
                }
                break;
            }
        }

        if (sourceLine.isEmpty()) {
            System.out.println("    DEBUG: End of File");
            nextToken = new Token(eofToken, getFileLineNum());
        } /* Check for end-of-file */

        if (nextToken != null) {
            System.out.println(nextToken.identify());
        }

        Main.log.noteToken(nextToken);
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

    private void checkAndRemoveComments() {
        if (sourceLine.startsWith("/*")) {
            boolean endOfComment = false;

            while (endOfComment != true) {

                String[] findEOC = sourceLine.split("\\s+");

                if (findEOC[findEOC.length - 1].equals("*/")) {
                    endOfComment = true;
                } /* when found; change boolean value */

                // Iterate the scanner header (we do not which to tockenize comments)
                readNextLine();
            } /* loop untill end of comment symbol is found */
            System.out.println("    DEBUG: End of comment tag found!");
            readNextToken();
        } /* statement checks if line of code starts with comment symbol */
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
