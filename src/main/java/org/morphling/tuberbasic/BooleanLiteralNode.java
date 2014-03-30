package org.morphling.tuberbasic;

import com.oracle.truffle.api.frame.VirtualFrame;

public class BooleanLiteralNode extends TuberNode {

    private final boolean value;

    public BooleanLiteralNode(boolean value) {
        this.value = value;
    }

    @Override
    public Object execute(VirtualFrame vf) {
        return value;
    }
}
