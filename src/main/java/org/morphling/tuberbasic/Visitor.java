package org.morphling.tuberbasic;

import org.antlr.v4.runtime.misc.NotNull;

import java.math.BigInteger;
import java.util.Arrays;

public class Visitor extends TuberBasicBaseVisitor<TuberNode> {
    @Override
    public TuberNode visitStringLiteral(@NotNull TuberBasicParser.StringLiteralContext ctx) {
        String foo = ctx.getChild(0).getText();
        String content = foo.substring(1, foo.length()-1);
        return new StringLiteralNode(content);
    }

    @Override
    public TuberNode visitNumberLiteral(@NotNull TuberBasicParser.NumberLiteralContext ctx) {
        return new NumberLiteralNode(new BigInteger(ctx.getChild(0).getText()));
    }

    @Override
    public TuberNode visitPrint(@NotNull TuberBasicParser.PrintContext ctx) {
        TuberNode formatStringExpression = visit(ctx.formatstring);
        // child 0 is "PRINT", child 1 is the format string
        // There has to be better code than this though.
        TuberNode[] nodes = new TuberNode[ctx.getChildCount()-2];
        for (int i = 0; i < ctx.getChildCount()-2; i++) {
            nodes[i] = (TuberNode) visit(ctx.getChild(i + 2));
        }
        System.out.println(Arrays.toString(nodes));
        return new PrintNode(formatStringExpression, nodes);
    }

    @Override
    public TuberNode visitExpression(@NotNull TuberBasicParser.ExpressionContext ctx) {
        return super.visitExpression(ctx);
    }

    @Override
    public TuberNode visitBasicfile(@NotNull TuberBasicParser.BasicfileContext ctx) {
        return super.visitBasicfile(ctx);
    }
}
