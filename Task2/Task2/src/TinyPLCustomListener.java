import java.util.ArrayList;
import java.util.List;

public class TinyPLCustomListener extends ExprPLBaseListener{

    private ExprPLParser.ExprContext tree;
    public static List<String> valuelist = new ArrayList<String>();

    @Override
    public void exitExpr(ExprPLParser.ExprContext ctx) {
        if(ctx.getChild(0)==ctx.True()){
            //valuelist.add("true");
            //Boolean.ParseBoolean(ctx.getChild(0).toString);
            System.out.println("true");
        }
        else  if(ctx.getChild(0)==ctx.False()){
            //valuelist.add("false");
            System.out.println("false");
        }
        else  if(ctx.getChild(0)==ctx.Not()){//only boolean
            //valuelist.add("!" + ctx.getChild(1).toString());
            boolean x = Boolean.parseBoolean(ctx.getChild(0).toString());
            boolean y = !x;
            System.out.println("!" + ctx.getChild(1).toString());
        }
        else  if(ctx.getChild(0) == ctx.Number()) {//only nat
            //valuelist.add(ctx.getChild(0).toString());
            System.out.println(ctx.getChild(0).toString());
        }
        else  if(ctx.getChild(1) == ctx.Plus()) {
            //valuelist.add(String.valueOf(Integer.parseInt(ctx.getChild(0).toString()) + Integer.parseInt(ctx.getChild(1).toString())));
            System.out.println(ctx.getChild(0).toString() + "+" + ctx.getChild(1).toString());
            //System.out.println("+");
        }
        else  if(ctx.getChild(1) == ctx.Equal()) {//either both nat or bool
            //valuelist.add(String.valueOf(ctx.getChild(0).toString().equals(ctx.getChild(1).toString())));
            System.out.println(ctx.getChild(0).toString() + "=" + ctx.getChild(1).toString());
            //System.out.println("=");
        }
        else if(ctx.getChild(1) == ctx.And()) {//only if both bool
            //valuelist.add(String.valueOf((Boolean.parseBoolean(ctx.getChild(0).toString()) && (Boolean.parseBoolean(ctx.getChild(1).toString())))));
            System.out.println(ctx.getChild(0).toString() + "&&" + ctx.getChild(1).toString());
            //System.out.println("&&");
        }
        else if(ctx.getChild(0) == ctx.If() ) {//either both bool or nat
            if(Boolean.parseBoolean(ctx.getChild(1).toString())){
                valuelist.add(ctx.getChild(3).toString());
            }else{
                valuelist.add(ctx.getChild(5).toString());
            }
        }
    }
}
