package org.morphling.tuberbasic;

import com.oracle.truffle.api.frame.VirtualFrame;

import java.math.BigInteger;

public class NumberLiteralNode extends TuberNode {
    private final BigInteger n;

    public NumberLiteralNode(BigInteger n) {
        super();
        this.n = n;
    }

    @Override
    public Object execute(VirtualFrame vf) {
        return n;
    }
}
