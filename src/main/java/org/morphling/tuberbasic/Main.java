package org.morphling.tuberbasic;

import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.TruffleRuntime;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;

import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        CharStream in = new ANTLRInputStream("PRINT 5");
        TuberBasicLexer lexer = new TuberBasicLexer(in);
        TokenStream tokens = new CommonTokenStream(lexer);
        TuberBasicParser parser = new TuberBasicParser(tokens);
        System.out.println(parser);


        TruffleRuntime runtime = Truffle.getRuntime();
        TuberRootNode rootNode = new TuberRootNode(new PrintNode("Hello, the answer is %s\n", new NumberLiteralNode(new BigInteger("42"))));
        CallTarget target = runtime.createCallTarget(rootNode);
        Object result = target.call();
        System.out.println(result);
    }
}
