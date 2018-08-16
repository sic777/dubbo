package com.sic777.utils.container.tuple;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-01-25 16:21
 */
public class FiveTuple<A, B, C, D, E> extends FourTuple<A, B, C, D> {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public final E fifth;

    public FiveTuple(A a, B b, C c, D d, E e) {
        super(a, b, c, d);
        this.fifth = e;
    }

    public String toString() {
        return "(" + first + ", " + second + ", " + third + ", " + fourth + ", " + fifth + ")";
    }
}
