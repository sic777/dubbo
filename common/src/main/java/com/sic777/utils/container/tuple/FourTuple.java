package com.sic777.utils.container.tuple;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-01-25 16:21
 */
public class FourTuple<A, B, C, D> extends ThreeTuple<A, B, C> {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public final D fourth;

    public FourTuple(A a, B b, C c, D d) {
        super(a, b, c);
        this.fourth = d;
    }

    public String toString() {
        return "(" + first + ", " + second + ", " + third + ", " + fourth + ")";
    }
}
