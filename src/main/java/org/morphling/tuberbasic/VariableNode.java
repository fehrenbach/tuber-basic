package org.morphling.tuberbasic;

import com.oracle.truffle.api.frame.FrameSlotTypeException;
import com.oracle.truffle.api.frame.VirtualFrame;

public class VariableNode extends TuberNode {
    private final String name;

    public VariableNode(String name) {
        this.name = name;
    }

    @Override
    public Object execute(VirtualFrame vf) {
        try {
            // TODO Probably should do the frame descriptor stuff at AST construction time.
            return vf.getObject(vf.getFrameDescriptor().findOrAddFrameSlot(name));
        } catch (FrameSlotTypeException e) {
            e.printStackTrace();
        }
        return null;
    }
}
