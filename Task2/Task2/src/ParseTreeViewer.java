import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.misc.Pair;

public class ParseTreeViewer {

    public static void main(String[] args) {
        // write code into string
        String code = "true && true";

        // parse code into AST
        Pair<ExprPLParser.ExprContext, ExprPLParser> pair = Util.parseCodeWithParser(code);
        ExprPLParser.ExprContext tree = pair.a;
        ExprPLParser parser = pair.b;

        // show AST in console
        System.out.println(tree.toStringTree(parser));

        // show AST in GUI
        JFrame frame = new JFrame("Antlr AST");
        JPanel panel = new JPanel();
        TreeViewer viewer = new TreeViewer(Arrays.asList(parser.getRuleNames()), tree);
        viewer.setScale(1.5); // Scale a little
        panel.add(viewer);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
