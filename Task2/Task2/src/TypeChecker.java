import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;

public class TypeChecker extends  ExprPLBaseListener{

    private ExprPLParser.ExprContext tree;
    public static Boolean typechecker = false;
    public TypeChecker(ExprPLParser.ExprContext tree) {
        this.tree = tree;
    }


    @Override
    public void exitExpr(ExprPLParser.ExprContext ctx) {
        if(ctx.getChild(0)==ctx.True()){
            //valuelist.add("true");
            //System.out.println("true");
            typechecker = true;
        }
        else if(ctx.getChild(0)==ctx.False()){
            typechecker = false;
        }
        else if(ctx.getChild(0)==ctx.Not()){
            //valuelist.add("!" + ctx.getChild(1).toString());
        }
        else   if(ctx.getChild(0) == ctx.Number()) {
            //valuelist.add(ctx.getChild(0).toString());
        }
        else   if (ctx.getChild(1) == ctx.Plus()) {
            //valuelist.add(String.valueOf(Integer.parseInt(ctx.getChild(0).toString()) + Integer.parseInt(ctx.getChild(1).toString())));
        }
        else    if (ctx.getChild(1) == ctx.Equal()) {
            //valuelist.add(String.valueOf(ctx.getChild(0).toString().equals(ctx.getChild(1).toString())));
        }
        else   if (ctx.getChild(1) == ctx.And()) {
            //valuelist.add(String.valueOf((Boolean.parseBoolean(ctx.getChild(0).toString()) && (Boolean.parseBoolean(ctx.getChild(1).toString())))));
        }
        else   if (ctx.getChild(0) == ctx.If() ) {
            if(Boolean.parseBoolean(ctx.getChild(1).toString())){
               // valuelist.add(ctx.getChild(3).toString());
            }else{
                //valuelist.add(ctx.getChild(5).toString());
            }
        }
    }

    public boolean check() {
        ExprPLBaseListener listener = new ExprPLBaseListener();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(listener, tree);
        return typechecker;
    }

    public Boolean run() {
        Boolean output;
       // valuelist.clear();
        ExprPLBaseListener listener = new ExprPLBaseListener();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(listener, tree);
        //System.out.println("values of method: " + lister());
        output = typechecker;
        //System.out.println("values of original: " + outputvalues);
        //System.out.println("values of output: " + output);
        return output;
    }
    public static void main(String[] args){
        String code = "true";
        ExprPLParser.ExprContext tree = Util.parseCode(code);
        TypeChecker checker = new TypeChecker(tree);
        assertFalse(checker.check());
        Boolean output = checker.run();
        System.out.println(output);
    }
}
