package org.morphling.tuberbasic;

import com.oracle.truffle.api.frame.VirtualFrame;

public class PrintNode extends TuberNode {
    private final TuberNode formatStringExpression;
    @Children
    private final TuberNode[] children;

    public PrintNode(TuberNode formatStringExpression, TuberNode... children) {
        this.formatStringExpression = formatStringExpression;
        this.children = children;
    }

    @Override
    public Object execute(VirtualFrame vf) {
        String formatString = (String) formatStringExpression.execute(vf);
        Object[] results = new Object[children.length];
        for (int i = 0; i < children.length; i++) {
            results[i] = children[i].execute(vf);
        }
        System.out.format(formatString, results);
        return null;
    }
}
