package org.morphling.tuberbasic;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;

public class CondNode extends TuberNode {
    @Children private final TuberNode[] tests;
    @Children private final TuberNode[] thens;

    public CondNode(TuberNode[] tests, TuberNode[] thens) {
        this.tests = tests;
        this.thens = thens;
    }

    @Override
    @ExplodeLoop
    public Object execute(VirtualFrame vf) {
        for (int i = 0; i < tests.length; i++) {
            if ((Boolean) tests[i].execute(vf) == true) {
                return thens[i].execute(vf);
            }
        }
        return null;
    }
}
