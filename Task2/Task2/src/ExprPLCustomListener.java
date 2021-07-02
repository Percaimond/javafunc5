import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExprPLCustomListener extends  ExprPLBaseListener{
    public static Boolean typechecker = false;
    public static Boolean falseexpressionflag = false;
    private ExprPLParser.ExprContext tree;



    public HashMap<Integer, String[]> analyzer = new HashMap<>();
    public static String[] infoType = new String[2];

    public static boolean isBoolean(String checking){
        return checking.equals("true") || checking.equals("false");
    }

    public static boolean isBoolean(String[] checking){
        return checking[1].equals("boolean");
    }

    public static boolean isNat(String[] checking){
        return checking[1].equals("nat");
    }
    public static boolean isNat(String checking){
        Pattern natural = Pattern.compile("[1-9][0-9]*");
        Matcher matcher = natural.matcher(checking);
        return matcher.find();
    }
    private boolean isfailed(String[] checking) {
        return checking[1].equals("failed");
    }


    @Override
    public void exitExpr(ExprPLParser.ExprContext ctx) {
        if (ctx.getChild(0) == ctx.True()) {
            typechecker = true;
            falseexpressionflag = false;
            //System.out.println("true?");
            infoType = new String[]{"true", "boolean"};
            analyzer.put(ctx.hashCode(),infoType);


        } else if (ctx.getChild(0) == ctx.False()) {
            if(isBoolean(ctx.getChild(0).toString())) {
            typechecker = true;
                falseexpressionflag = false;
            //System.out.println("false?");
            infoType = new String[]{"true", "boolean"};
            analyzer.put(ctx.hashCode(),infoType);
            }








        } else if (ctx.getChild(0) == ctx.Not()) {//noch fehlerhaft

            if(isNat(analyzer.get(ctx.getChild(1).hashCode()))) {
                falseexpressionflag = true;
                typechecker = false;
                infoType = new String[]{"false", "failed"};
                //
                analyzer.put(ctx.hashCode(),infoType);
            }else if((isBoolean(analyzer.get(ctx.getChild(1).hashCode())))) {
                typechecker = true;
                falseexpressionflag = false;
                //System.out.println("not?");
                infoType = new String[]{"true", "boolean"};
                analyzer.put(ctx.hashCode(),infoType);
            }
            System.out.println("not?");
        }
        else if (ctx.getChild(0) == ctx.Number()) {
                if(isNat(ctx.getChild(0).toString())){
                typechecker = true;
                falseexpressionflag = false;
                //System.out.println("number?");
                infoType = new String[]{"true", "nat"};
                analyzer.put(ctx.hashCode(), infoType);}
            }



        else if (ctx.getChild(1) == ctx.Plus()) {
            if (isBoolean(analyzer.get(ctx.getChild(0).hashCode())) || isBoolean(analyzer.get(ctx.getChild(2).hashCode()))) {
                falseexpressionflag = true;
                typechecker = false;
                infoType = new String[]{"false", "failed"};
                analyzer.put(ctx.hashCode(),infoType);
            } else if(isNat(analyzer.get(ctx.getChild(0).hashCode())) && isNat(analyzer.get(ctx.getChild(2).hashCode()))){
                infoType = new String[]{"true", "nat"};
                analyzer.put(ctx.hashCode(),infoType);
            }









        } else if (ctx.getChild(1) == ctx.Equal()) {
                if (((isBoolean(analyzer.get(ctx.getChild(0).hashCode())) && (isNat(analyzer.get(ctx.getChild(2).hashCode()))) ||
                        ((isNat((analyzer.get(ctx.getChild(0).hashCode()))) && (isBoolean((analyzer.get(ctx.getChild(2).hashCode())))))) ||
                        ((isfailed((analyzer.get(ctx.getChild(0).hashCode()))) || (isfailed((analyzer.get(ctx.getChild(2).hashCode())))))))))
                {
                    falseexpressionflag = true;
                    typechecker = false;
                    infoType = new String[]{"false", "failed"};
                    analyzer.put(ctx.hashCode(),infoType);
                    System.out.println("first");
                } else if(((isBoolean(analyzer.get(ctx.getChild(0).hashCode())) && (isBoolean(analyzer.get(ctx.getChild(2).hashCode()))))))  {
                    typechecker = true;
                    falseexpressionflag = false;
                    infoType = new String[]{"true", "boolean"};
                    analyzer.put(ctx.hashCode(),infoType);
                    System.out.println("second");
                } else if(((isNat(analyzer.get(ctx.getChild(0).hashCode())) && (isNat(analyzer.get(ctx.getChild(2).hashCode())))))) {
                    typechecker = true;
                    falseexpressionflag = false;
                    infoType = new String[]{"true", "boolean"};
                    analyzer.put(ctx.hashCode(),infoType);
                    System.out.println("third");
                }
                System.out.println("equal?");






            } else if (ctx.getChild(1) == ctx.And()) {
                if (((isNat(analyzer.get(ctx.getChild(0).hashCode())) || (isNat(analyzer.get(ctx.getChild(2).hashCode())))))) {
                    falseexpressionflag = true;
                    typechecker = false;
                    infoType = new String[]{"false", "failed"};
                    analyzer.put(ctx.hashCode(),infoType);

                } else if((isBoolean(analyzer.get(ctx.getChild(0).hashCode()))) && (isBoolean(analyzer.get(ctx.getChild(2).hashCode())))) {
                    falseexpressionflag = false;
                    typechecker = true;
                    infoType = new String[]{"true", "boolean"};
                    analyzer.put(ctx.hashCode(),infoType);

                }

            System.out.println("and?");




            }
        else if (ctx.getChild(0) == ctx.If()) {

            if(isfailed(analyzer.get(ctx.getChild(1).hashCode()))){
                falseexpressionflag = true;
                typechecker = false;
                infoType = new String[]{"false", "failed"};
                analyzer.put(ctx.hashCode(),infoType);
            }else if(isBoolean(analyzer.get(ctx.getChild(1).hashCode()))){
                if (((isBoolean(analyzer.get(ctx.getChild(3).hashCode()))) && (isNat(analyzer.get(ctx.getChild(5).hashCode()))) ||
                        (isNat(analyzer.get(ctx.getChild(3).hashCode()))) && (isBoolean(analyzer.get(ctx.getChild(5).hashCode()))))) {
                    falseexpressionflag = true;
                    typechecker = false;
                    infoType = new String[]{"false", "failed"};
                    //System.out.println("if statement");
                } else if (((isBoolean(analyzer.get(ctx.getChild(3).hashCode()))) && (isBoolean(analyzer.get(ctx.getChild(5).hashCode()))))) {
                    typechecker = true;
                    infoType = new String[]{"true", "boolean"};
                    analyzer.put(ctx.hashCode(), infoType);
                } else if (((isNat(analyzer.get(ctx.getChild(3).hashCode()))) && (isNat(analyzer.get(ctx.getChild(5).hashCode()))))) {
                    infoType = new String[]{"true", "nat"};
                    analyzer.put(ctx.hashCode(), infoType);
                }
            }
            }}


}

