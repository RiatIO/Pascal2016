package parser;

import main.*;
import scanner.*;
import static scanner.TokenKind.*;

abstract class Type extends PascalSyntax {

    Type(int lNum) {
        super(lNum);
    }

    @Override public String identify() {
        return "<type> on line " + lineNum;
    }

    static Type parse(Scanner s) {
        enterParser("type");
        Type t = null;

<<<<<<< HEAD

        if(s.curToken.kind == nameToken){
            t.name = s.curToken.id;
            //s.test(nameToken); 
        }else{
=======
        if (s.curToken.kind == nameToken) {
            t = TypeName.parse(s);
        } else {
>>>>>>> 2383d82e46b7d71a889a587a4f9cafabbf009a13
            t = ArrayType.parse(s);
        }

        leaveParser("type");
        return t;
    }
}
