import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.Pair;

public class Util {

    public static ExprPLParser.ExprContext parseCode(String code) {
        return parseCodeWithParser(code).a;
    }

    public static Pair<ExprPLParser.ExprContext, ExprPLParser> parseCodeWithParser(String code) {
        ExprPLLexer lexer = new ExprPLLexer(CharStreams.fromString(code));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ExprPLParser parser = new ExprPLParser(tokens);
        ExprPLParser.ExprContext tree = parser.expr();
        return new Pair<ExprPLParser.ExprContext, ExprPLParser>(tree, parser);
    }

}
