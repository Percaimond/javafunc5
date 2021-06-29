// Generated from src/ExprPL.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ExprPLParser}.
 */
public interface ExprPLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ExprPLParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(ExprPLParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprPLParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(ExprPLParser.ExprContext ctx);
}