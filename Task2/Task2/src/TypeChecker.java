
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class TypeChecker extends  ExprPLCustomListener{

    private ExprPLParser.ExprContext tree;

    public TypeChecker(ExprPLParser.ExprContext tree) {
        this.tree = tree;
    }

    public boolean check() {
        ExprPLCustomListener listener = new ExprPLCustomListener();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(listener, tree);

        if(falseexpressionflag){
            return  false;
        } else {
            return typechecker;
        }
    }

    public static void main(String[] args){
        String code = "if if if true then false else false then false else false then 1 else 4";
        ExprPLParser.ExprContext tree = Util.parseCode(code);
        TypeChecker checker = new TypeChecker(tree);
        System.out.println(checker.check());
    }
}
