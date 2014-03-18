package org.morphling.tuberbasic;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;

public abstract class TuberNode extends Node {
    public abstract Object execute(VirtualFrame vf);
}
