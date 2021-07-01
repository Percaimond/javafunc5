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
    @Test
    public void testExpression5() {
        String code = "if 2 + 3 = 5 then 24 else 25";
        ExprPLParser.ExprContext tree = Util.parseCode(code);
        TypeChecker checker = new TypeChecker(tree);
        assertTrue(checker.check());
    }

    @Test
    public void testExpression6() {
        String code = "false && false = 5 = !4";
        ExprPLParser.ExprContext tree = Util.parseCode(code);
        TypeChecker checker = new TypeChecker(tree);
        assertFalse(checker.check());
    }

    @Test
    public void testExpression7() {
        String code = "!6 = !6";
        ExprPLParser.ExprContext tree = Util.parseCode(code);
        TypeChecker checker = new TypeChecker(tree);
        assertTrue(checker.check());
    }

    @Test
    public void testExpression8() {
        String code = "if true = true then false else true";
        ExprPLParser.ExprContext tree = Util.parseCode(code);
        TypeChecker checker = new TypeChecker(tree);
        assertTrue(checker.check());
    }
    @Test
    public void testExpression9() {
        String code = "1 + 2 + 4 + 3 = !10 ";
        ExprPLParser.ExprContext tree = Util.parseCode(code);
        TypeChecker checker = new TypeChecker(tree);
        assertTrue(checker.check());
    }

    @Test
    public void testExpression10() {
        String code = "false";
        ExprPLParser.ExprContext tree = Util.parseCode(code);
        TypeChecker checker = new TypeChecker(tree);
        assertTrue(checker.check());
    }

    @Test
    public void testExpression11() {
        String code = "!8 = 9";
        ExprPLParser.ExprContext tree = Util.parseCode(code);
        TypeChecker checker = new TypeChecker(tree);
        assertTrue(checker.check());
    }

    @Test
    public void testExpression12() {
        String code = "1 + 2 && false";
        ExprPLParser.ExprContext tree = Util.parseCode(code);
        TypeChecker checker = new TypeChecker(tree);
        assertFalse(checker.check());
    }

}
