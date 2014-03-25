package org.morphling.tuberbasic;

public abstract class TuberBinaryRelationNode extends TuberNode {
    protected final
    @Child
    TuberNode left;
    protected final
    @Child
    TuberNode right;

    public TuberBinaryRelationNode(TuberNode left, TuberNode right) {
        this.left = left;
        this.right = right;
    }
}
