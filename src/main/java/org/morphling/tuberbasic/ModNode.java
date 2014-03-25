package org.morphling.tuberbasic;

import com.oracle.truffle.api.frame.VirtualFrame;

import java.math.BigInteger;

public class ModNode extends TuberNode {
    private final
    @Child
    TuberNode first;
    private final
    @Child
    TuberNode second;

    public ModNode(TuberNode first, TuberNode second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public Object execute(VirtualFrame vf) {
        BigInteger first = (BigInteger) this.first.execute(vf);
        BigInteger second = (BigInteger) this.second.execute(vf);
        return first.mod(second);
    }
}
