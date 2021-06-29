public class TypeChecker {

    private ExprPLParser.ExprContext tree;

    public TypeChecker(ExprPLParser.ExprContext tree) {
        this.tree = tree;
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
