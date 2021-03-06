package org.morphling.tuberbasic;

import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.TruffleRuntime;
import org.antlr.v4.runtime.*;

import java.io.FileInputStream;

public class Main {
    public static void main(String[] args) throws Exception {
        CharStream in = new ANTLRInputStream(new FileInputStream(args[0]));
        TuberBasicLexer lexer = new TuberBasicLexer(in);
        TokenStream tokens = new CommonTokenStream(lexer);
        TuberBasicParser parser = new TuberBasicParser(tokens);
        ParserRuleContext tree = parser.statements();

        Visitor visitor = new Visitor();
        TuberNode ast = visitor.visit(tree);

        TruffleRuntime runtime = Truffle.getRuntime();
        TuberRootNode rootNode = new TuberRootNode(ast);
        CallTarget target = runtime.createCallTarget(rootNode);
        Object result = target.call();
        System.out.println(result);
    }
}
