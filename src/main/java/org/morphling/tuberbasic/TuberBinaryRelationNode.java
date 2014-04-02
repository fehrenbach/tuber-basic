package org.morphling.tuberbasic;

public abstract class TuberBinaryRelationNode extends TuberNode {
    protected
    @Child
    TuberNode left;
    protected
    @Child
    TuberNode right;

    public TuberBinaryRelationNode(TuberNode left, TuberNode right) {
        this.left = left;
        this.right = right;
    }
}
