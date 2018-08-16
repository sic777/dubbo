package com.sic777.utils.container.tuple;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-01-25 16:21
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
