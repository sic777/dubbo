package com.sic777.common.utils.container.tuple;


/**
 * <p>
 *
 * @author sic777
 * @since 0.0.1
 */
public class TwoTuple<A, B> implements java.io.Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public final A first;
    public final B second;

    public TwoTuple(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}
