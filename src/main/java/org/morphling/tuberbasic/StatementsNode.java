package org.morphling.tuberbasic;

import com.oracle.truffle.api.frame.VirtualFrame;

public class StatementsNode extends TuberNode {
    private final
    @Children
    TuberNode[] statements;

    public StatementsNode(TuberNode[] statements) {
        this.statements = statements;
    }

    @Override
    public Object execute(VirtualFrame vf) {
        for (TuberNode statement : statements) {
            statement.execute(vf);
        }
        return null;
    }
}
