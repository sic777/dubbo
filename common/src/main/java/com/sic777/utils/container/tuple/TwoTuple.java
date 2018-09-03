package com.sic777.utils.container.tuple;

import com.sic777.utils.proguard.NoProguard;

/**
 * <p>
 *
 * @author sic777
 * @since 0.0.1
 */
@NoProguard
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
