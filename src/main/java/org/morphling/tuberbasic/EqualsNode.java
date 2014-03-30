package org.morphling.tuberbasic;

import com.oracle.truffle.api.frame.VirtualFrame;

public class EqualsNode extends TuberBinaryRelationNode {
    public EqualsNode(TuberNode left, TuberNode right) {
        super(left, right);
    }

    @Override
    public Object execute(VirtualFrame vf) {
        Object l = left.execute(vf);
        Object r = right.execute(vf);
        return l.equals(r);
    }
}
