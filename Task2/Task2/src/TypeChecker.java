import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;

public class TypeChecker extends  ExprPLBaseListener{

    private ExprPLParser.ExprContext tree;

    public TypeChecker(ExprPLParser.ExprContext tree) {
        this.tree = tree;
    }

    @Override
    public void exitEveryRule(ParserRuleContext ctx) {
        super.exitEveryRule(ctx);
    }

    @Override
    public void enterEveryRule(ParserRuleContext ctx) {
        super.enterEveryRule(ctx);
    }
    //need method to recursively find the end and evaluate expression then work
    //bottom to top expression
    @Override
    public void enterExpr(ExprPLParser.ExprContext ctx) {
        if (ctx.getChild(0) == ctx.Not()) {
            //ctx.getChild(1) = !(Integer.valueOf(ctx.getChild(1).toString()));
        }
        if (ctx.getChild(0) == ctx.True()) {
            //ctx.getChild(1) = !(Integer.valueOf(ctx.getChild(1).toString()));
        }
        if (ctx.getChild(0) == ctx.False()) {
            //ctx.getChild(1) = !(Integer.valueOf(ctx.getChild(1).toString()));
        }
        if (ctx.getChild(0) == ctx.Number()) {
            //ctx.getChild(1) = !(Integer.valueOf(ctx.getChild(1).toString()));
        }
        if (ctx.getChild(0) == ctx.Not()) {
            //ctx.getChild(1) = !(Integer.valueOf(ctx.getChild(1).toString()));
        }
        if (ctx.getChild(1) == ctx.Plus()) {
            //ctx.getChild(1) = !(Integer.valueOf(ctx.getChild(1).toString()));
        }
        if (ctx.getChild(1) == ctx.Equal()) {
            //ctx.getChild(1) = !(Integer.valueOf(ctx.getChild(1).toString()));
        }
        if (ctx.getChild(1) == ctx.And()) {
            //ctx.getChild(1) = !(Integer.valueOf(ctx.getChild(1).toString()));
        }
        if (ctx.getChild(0) == ctx.If() ) {
            //ctx.getChild(1) = !(Integer.valueOf(ctx.getChild(1).toString()));
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

        return false;
    }

}
