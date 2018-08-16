package com.sic777.utils.container.tuple;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-01-25 16:21
 */
public class ThreeTuple<A, B, C> extends TwoTuple<A, B> {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public final C third;

    public ThreeTuple(A a, B b, C c) {
        super(a, b);
        this.third = c;
    }

    public String toString() {
        return "(" + first + ", " + second + ", " + third + ")";
    }
}
