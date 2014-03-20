package org.morphling.tuberbasic;

import com.oracle.truffle.api.frame.VirtualFrame;

public class StringLiteralNode extends TuberNode {
    private final String content;

    @Override
    public Object execute(VirtualFrame vf) {
        return content;
    }

    public StringLiteralNode(String content) {
        this.content = content;
    }
}
