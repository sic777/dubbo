package com.sic777.utils.container.tuple;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-01-25 16:21
 */
public class EightTuple<A, B, C, D, E, F, G, H> extends SevenTuple<A, B, C, D, E, F, G> {

    private static final long serialVersionUID = 1L;
    public final H eighth;

    public EightTuple(A first, B second, C third, D fourth, E fifth, F sixth, G seventh, H eighth) {
        super(first, second, third, fourth, fifth, sixth, seventh);
        this.eighth = eighth;
    }
}
