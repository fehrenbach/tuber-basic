package org.morphling.tuberbasic;

import com.oracle.truffle.api.frame.VirtualFrame;

public class AndNode extends TuberBinaryRelationNode {
    public AndNode(TuberNode left, TuberNode right) {
        super(left, right);
    }

    @Override
    public Object execute(VirtualFrame vf) {
        if (left.execute(vf) == true) {
            return right.execute(vf);
        }
        return false;
    }
}
