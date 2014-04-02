package org.morphling.tuberbasic;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.RootNode;

class TuberRootNode extends RootNode {
    @Child
    private TuberNode program;

    TuberRootNode(TuberNode program) {
        this.program = program;
    }

    @Override
    public Object execute(VirtualFrame virtualFrame) {
        return program.execute(virtualFrame);
    }
}