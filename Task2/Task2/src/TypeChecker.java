import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;

public class TypeChecker extends  ExprPLBaseListener{

    private ExprPLParser.ExprContext tree;
    public static List<String> valuelist = new ArrayList<String>();
    public TypeChecker(ExprPLParser.ExprContext tree) {
        this.tree = tree;
    }


    @Override
    public void exitExpr(ExprPLParser.ExprContext ctx) {
        if(ctx.getChild(0)==ctx.True()){
            valuelist.add("true");
            System.out.println("true");
        }
        if(ctx.getChild(0)==ctx.False()){
            valuelist.add("false");
        }
        if(ctx.getChild(0)==ctx.Not()){
            valuelist.add("!" + ctx.getChild(1).toString());
        }
        if (ctx.getChild(0) == ctx.Number()) {
            valuelist.add(ctx.getChild(0).toString());
        }
        if (ctx.getChild(1) == ctx.Plus()) {
            valuelist.add(String.valueOf(Integer.parseInt(ctx.getChild(0).toString()) + Integer.parseInt(ctx.getChild(1).toString())));
        }
        if (ctx.getChild(1) == ctx.Equal()) {
            valuelist.add(String.valueOf(ctx.getChild(0).toString().equals(ctx.getChild(1).toString())));
        }
        if (ctx.getChild(1) == ctx.And()) {
            valuelist.add(String.valueOf((Boolean.parseBoolean(ctx.getChild(0).toString()) && (Boolean.parseBoolean(ctx.getChild(1).toString())))));
        }
        if (ctx.getChild(0) == ctx.If() ) {
            if(Boolean.parseBoolean(ctx.getChild(1).toString())){
                valuelist.add(ctx.getChild(3).toString());
            }else{
                valuelist.add(ctx.getChild(5).toString());
            }
        }
    }

    @Override
    public void visitTerminal(TerminalNode node) {
        super.visitTerminal(node);
    }

    public boolean check() {
        // TODO: implement
        // literal --int
        // booleans --true|false
        // boolean negation of integer  --!int
        //conjunction &&
        //Addition checker +
        //integer equality =
        //if e then e else e --if e == true {e} else e
        //exitExpr(tree);
        ExprPLBaseListener listener = new ExprPLBaseListener();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(listener, this.tree);


       // System.out.print(valuelist);
        //System.out.println(stack.peek());

        return false;
    }
    public static void main(String[] args){
        String code = "true + 2";
        ExprPLParser.ExprContext tree = Util.parseCode(code);
        TypeChecker checker = new TypeChecker(tree);
        assertFalse(checker.check());
        ExprPLBaseListener listener = new ExprPLBaseListener();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(listener, tree);


        System.out.print(valuelist);
    }
}
