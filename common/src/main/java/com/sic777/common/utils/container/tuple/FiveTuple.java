package com.sic777.common.utils.container.tuple;

import com.sic777.common.utils.proguard.NoProguard;

/**
 * <p>
 *
 * @author sic777
 * @since 0.0.1
 */
@NoProguard
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
