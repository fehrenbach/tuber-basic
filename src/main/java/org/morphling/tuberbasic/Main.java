package org.morphling.tuberbasic;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.InputStream;

public class Main {
    public static void main(String[] args) throws Exception {
        CharStream in = new ANTLRInputStream("PRINT 5");
        TuberBasicLexer lexer = new TuberBasicLexer(in);
        TokenStream tokens = new CommonTokenStream(lexer);
        TuberBasicParser parser = new TuberBasicParser(tokens);
        System.out.println(parser);
    }
}
