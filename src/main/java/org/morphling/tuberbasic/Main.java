package org.morphling.tuberbasic;

import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.TruffleRuntime;
import org.antlr.v4.runtime.*;

public class Main {
    public static void main(String[] args) throws Exception {
        CharStream in = new ANTLRInputStream("PRINT \"Hello, the answer is %s%n\" 42\n" +
                "PRINT \"foo%n\"");
        TuberBasicLexer lexer = new TuberBasicLexer(in);
        TokenStream tokens = new CommonTokenStream(lexer);
        TuberBasicParser parser = new TuberBasicParser(tokens);
        ParserRuleContext tree = parser.statements();

        Visitor visitor = new Visitor();
        TuberNode ast = visitor.visit(tree);
        System.out.println("Result: " + ast);

        TruffleRuntime runtime = Truffle.getRuntime();
        //TuberRootNode rootNode = new TuberRootNode(new PrintNode(new StringLiteralNode("Hello, the answer is %s\n"), new NumberLiteralNode(new BigInteger("42"))));
        TuberRootNode rootNode = new TuberRootNode(ast);
        CallTarget target = runtime.createCallTarget(rootNode);
        Object result = target.call();
        System.out.println(result);
    }
}
