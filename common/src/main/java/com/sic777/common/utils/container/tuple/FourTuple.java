package com.sic777.common.utils.container.tuple;

import com.sic777.common.utils.proguard.NoProguard;

/**
 * <p>
 *
 * @author sic777
 * @since 0.0.1
 */
@NoProguard
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
