package com.sic777.utils.container.tuple;

/**
 * <p>
 *
 * @author sic777
 * @since 0.0.1
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
