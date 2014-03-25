package org.morphling.tuberbasic;

import com.oracle.truffle.api.frame.VirtualFrame;

public class EqualsNode extends TuberBinaryRelationNode {
    public EqualsNode(TuberNode left, TuberNode right) {
        super(left, right);
    }

    @Override
    public Object execute(VirtualFrame vf) {
        return left.execute(vf).equals(right.execute(vf));
    }
}
