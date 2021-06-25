import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TypeCheckerTest {

    @Test
    public void testExpression1() {
        String code = "true + 2";
        ExprPLParser.ExprContext tree = Util.parseCode(code);
        TypeChecker checker = new TypeChecker(tree);
        assertFalse(checker.check());
    }

    @Test
    public void testExpression2() {
        String code = "true";
        ExprPLParser.ExprContext tree = Util.parseCode(code);
        TypeChecker checker = new TypeChecker(tree);
        assertTrue(checker.check());
    }

    @Test
    public void testExpression3() {
        String code = "2 + 4 + 5";
        ExprPLParser.ExprContext tree = Util.parseCode(code);
        TypeChecker checker = new TypeChecker(tree);
        assertTrue(checker.check());
    }

    @Test
    public void testExpression4() {
        String code = "if 2 = 3 then 27 else true";
        ExprPLParser.ExprContext tree = Util.parseCode(code);
        TypeChecker checker = new TypeChecker(tree);
        assertFalse(checker.check());
    }

}
