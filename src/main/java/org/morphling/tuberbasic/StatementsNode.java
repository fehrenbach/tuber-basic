package org.morphling.tuberbasic;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;

public class StatementsNode extends TuberNode {
    private final
    @Children
    TuberNode[] statements;

    public StatementsNode(TuberNode... statements) {
        this.statements = statements;
    }

    @Override
    @ExplodeLoop
    public Object execute(VirtualFrame vf) {
        for (TuberNode statement : statements) {
            statement.execute(vf);
        }
        return null;
    }
}
