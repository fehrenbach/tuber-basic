package org.morphling.tuberbasic;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTree;

import java.math.BigInteger;

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
        return new PrintNode(formatStringExpression, nodes);
    }

    @Override
    public TuberNode visitBinaryFunction(@NotNull TuberBasicParser.BinaryFunctionContext ctx) {
        switch (ctx.function.getText()) {
            case "mod":
                return new ModNode(visit(ctx.first), visit(ctx.second));
            default:
                throw new RuntimeException("Unknown function " + ctx.function.getText());
        }
    }

    @Override
    public TuberNode visitBooleanAnd(@NotNull TuberBasicParser.BooleanAndContext ctx) {
        TuberNode left = visit(ctx.left);
        TuberNode right = visit(ctx.right);
        return new AndNode(left, right);
    }

    @Override
    public TuberNode visitBooleanLiteralTrue(@NotNull TuberBasicParser.BooleanLiteralTrueContext ctx) {
        return new BooleanLiteralNode(true);
    }

    @Override
    public TuberNode visitBooleanLiteralFalse(@NotNull TuberBasicParser.BooleanLiteralFalseContext ctx) {
        return new BooleanLiteralNode(false);
    }

    @Override
    public TuberNode visitForLoop(@NotNull TuberBasicParser.ForLoopContext ctx) {
        String varName = ctx.var.getText();

        TuberNode from = visit(ctx.from);
        TuberNode to = visit(ctx.to);

        TuberNode statements = visit(ctx.statements());

        return new ForLoopNode(varName, from, to, statements);
    }

    @Override
    public TuberNode visitVariable(@NotNull TuberBasicParser.VariableContext ctx) {
        String name = ctx.getText();
        return new VariableNode(name);
    }

    @Override
    public TuberNode visitCond(@NotNull TuberBasicParser.CondContext ctx) {
        TuberNode[] tests = new TuberNode[ctx.expression().size()];
        TuberNode[] thens = new TuberNode[ctx.statements().size()];
        for (int i = 0; i < ctx.expression().size(); i++) {
            tests[i] = visit(ctx.expression(i));
            thens[i] = visit(ctx.statements(i));
        }
        return new CondNode(tests, thens);
    }

    @Override
    public TuberNode visitEquals(@NotNull TuberBasicParser.EqualsContext ctx) {
        return new EqualsNode(visit(ctx.left), visit(ctx.right));
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
