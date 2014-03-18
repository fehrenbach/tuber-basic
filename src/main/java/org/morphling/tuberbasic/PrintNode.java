package org.morphling.tuberbasic;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;

public class PrintNode extends TuberNode {
    private final String formatString;
    @Node.Children
    private final TuberNode[] children;

    public PrintNode(String formatString, TuberNode... children) {
        this.formatString = formatString;
        this.children = children;
    }

    @Override
    public Object execute(VirtualFrame vf) {
        Object[] results = new Object[children.length];
        for (int i = 0; i < children.length; i++) {
            results[i] = children[i].execute(vf);
        }
        System.out.format(formatString, results);
        return null;
    }
}
