package org.morphling.tuberbasic;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTree;

import java.math.BigInteger;
import java.util.Arrays;

public class Visitor extends TuberBasicBaseVisitor<TuberNode> {
    @Override
    public TuberNode visitStringLiteral(@NotNull TuberBasicParser.StringLiteralContext ctx) {
        String foo = ctx.getChild(0).getText();
        String content = foo.substring(1, foo.length() - 1);
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
        TuberNode[] nodes = new TuberNode[ctx.getChildCount() - 2];
        for (int i = 0; i < ctx.getChildCount() - 2; i++) {
            nodes[i] = visit(ctx.getChild(i + 2));
        }
        System.out.println(Arrays.toString(nodes));
        return new PrintNode(formatStringExpression, nodes);
    }

    @Override
    public TuberNode visitStatements(@NotNull TuberBasicParser.StatementsContext ctx) {
        int actualStatementsCount = (ctx.getChildCount() + 1) / 2;
        TuberNode[] statements = new TuberNode[actualStatementsCount];
        statements[0] = visit(ctx.getChild(0));
        for (int i = 0; i < actualStatementsCount - 1; i++) {
            ParseTree child = ctx.getChild(2 * i + 2);
            statements[i + 1] = visit(child);
        }
        return new StatementsNode(statements);
    }
}
