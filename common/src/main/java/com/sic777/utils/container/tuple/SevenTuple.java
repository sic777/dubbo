package com.sic777.utils.container.tuple;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-01-25 16:21
 */
public class SevenTuple<A, B, C, D, E, F, G> extends SixTuple<A, B, C, D, E, F> {

    private static final long serialVersionUID = 1L;
    public final G seventh;

    public SevenTuple(A first, B second, C third, D fourth, E fifth, F sixth, G seventh) {
        super(first, second, third, fourth, fifth, sixth);
        this.seventh = seventh;
    }
}
