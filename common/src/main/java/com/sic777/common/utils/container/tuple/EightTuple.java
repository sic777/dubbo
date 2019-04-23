package com.sic777.common.utils.container.tuple;


/**
 * <p>
 *
 * @author sic777
 * @since 0.0.1
 */
public class EightTuple<A, B, C, D, E, F, G, H> extends SevenTuple<A, B, C, D, E, F, G> {

    private static final long serialVersionUID = 1L;
    public final H eighth;

    public EightTuple(A first, B second, C third, D fourth, E fifth, F sixth, G seventh, H eighth) {
        super(first, second, third, fourth, fifth, sixth, seventh);
        this.eighth = eighth;
    }
}
