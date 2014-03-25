package org.morphling.tuberbasic;

import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.VirtualFrame;

import java.math.BigInteger;

public class ForLoopNode extends TuberNode {
    private final String variableName;

    private final
    @Child
    TuberNode from;
    private final
    @Child
    TuberNode to;
    private final
    @Child
    TuberNode body;

    public ForLoopNode(String varName, TuberNode from, TuberNode to, TuberNode statements) {
        this.variableName = varName;
        this.from = from;
        this.to = to;
        this.body = statements;
    }

    @Override
    public Object execute(VirtualFrame vf) {
        BigInteger from = (BigInteger) this.from.execute(vf);
        BigInteger to = (BigInteger) this.to.execute(vf);

        // TODO I think the scoping is severely broken. If the variable is already bound, we reuse its frame slot and later remove it entirely...
        // TODO Also, it's probably not a good idea to do the frame descriptor stuff here. Should probably do that during AST construction.
        FrameDescriptor fd = vf.getFrameDescriptor();
        FrameSlot var = fd.findOrAddFrameSlot(variableName);

        vf.setObject(var, from);

        while (((BigInteger) vf.getValue(var)).compareTo(to) < 0) {
            body.execute(vf);
            vf.setObject(var, ((BigInteger) vf.getValue(var)).add(BigInteger.ONE));
        }

        fd.removeFrameSlot(var);

        return null;
    }
}
