package org.morphling.tuberbasic;

import com.oracle.truffle.api.frame.VirtualFrame;

public class CondNode extends TuberNode {
    private final
    @Children
    TuberNode[] tests;
    private final
    @Children
    TuberNode[] thens;

    public CondNode(TuberNode[] tests, TuberNode[] thens) {
        this.tests = tests;
        this.thens = thens;
    }

    @Override
    public Object execute(VirtualFrame vf) {
        for (int i = 0; i < tests.length; i++) {
            if (tests[i].execute(vf) == true) {
                return thens[i].execute(vf);
            }
        }
        return null;
    }
}
