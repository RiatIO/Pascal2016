package scanner;

import main.Main;
import static scanner.TokenKind.*;

import java.io.*;

public class Scanner {
    public Token curToken = null, nextToken = null;

    private LineNumberReader sourceFile = null;
    private String sourceFileName, sourceLine = "";
    private int sourcePos = 0;

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
        if (sourceLine.startsWith("/*")) {
            boolean endOfComment = false;

            while (endOfComment != true) {
                String[] findEOC = sourceLine.split("\\s+");

                if (findEOC[findEOC.length - 1].equals("*/")) {
                    System.out.println("DEBUG: End of comment tag found!");
                    endOfComment = true;
                } /* when found; change boolean value */

                // Iterate the scanner header (we do not which to tockenize comments)
                readNextLine();
            } /* loop untill end of comment symbol is found */
        } /* statement checks if line of code starts with comment symbol */

        

        //Main.log.noteToken(nextToken);
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
