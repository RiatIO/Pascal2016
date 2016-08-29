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

        readNextToken();
        readNextToken();
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

        boolean isTokenFound = false;
        //while (!isTokenFound) {
            if (sourceLine.isEmpty()) {
                nextToken = new Token(eofToken, getFileLineNum());
            }

            if (sourcePos + 1 > sourceLine.length() || sourceLine.length() <= 1) {
                System.out.println("    DEBUG: Empty line found");
                readNextLine();
            }

            System.out.println(String.format("  %d(%d): %s(%d)", getFileLineNum(), sourcePos, sourceLine, sourceLine.length()));

            // Check current sourceLine for comments and remove if so
            checkAndRemoveComments();

            for (int i = sourcePos; i < sourceLine.length(); i++) {
                //System.out.println("Current line: " + sourceLine + " LINE " +sourceLine.length());
                char curr = sourceLine.charAt(i);
                sourcePos++;
                System.out.format("curr: %c - pos: %d\n", curr, sourcePos);

                if (isLetterAZ(curr) || isDigit(curr)) {
                    buffer.append(curr);
                }
                else if (curr == ' ' && buffer.length() == 0) {
                    System.out.println("Length: " + sourcePos);
                    continue;
                }
                else if (curr == ' ') {

                    String token = buffer.toString().toLowerCase();
                    nextToken = new Token(token, getFileLineNum());

                    System.out.println("Scanner: " + nextToken.identify());

                    buffer.setLength(0);
                    isTokenFound = true;
                    break;
                }
                else {
                    if (!buffer.toString().isEmpty()) {
                        String token = buffer.toString().toLowerCase();
                        nextToken    = new Token(token, getFileLineNum());

                        buffer.setLength(0);
                        sourcePos--;
                    } else {
                        nextToken = new Token(curr, getFileLineNum());
                    }

                    System.out.println("Scanner: " + nextToken.identify());
                    isTokenFound = true;
                    break;
                }
            }
        //}
        //System.out.println("Line: " + sourceLine + " - length: " + sourceLine.length());

        /*
        if (curToken != null && curToken.kind == endToken) {
            System.out.println("IM DOOOONE DONE");
            nextToken = new Token(eofToken, getFileLineNum());
        }*/

        //System.out.println("SOURCEPOS: " + sourcePos + " stringlength: " + sourceLine.length());

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
