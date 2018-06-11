package com.sic777.utils;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-01-10 17:43
 */
public class BooleanUtil {
    /**
     * boolean转int,true转1，false转0
     *
     * @param b
     * @return
     */
    public static int bool2Int(boolean b) {
        return b ? 1 : 0;
    }

    /**
     * int转boolean,1转true,其他转false
     *
     * @param i
     * @return
     */
    public static boolean int2Bool(int i) {
        return i == 1;
    }
}
