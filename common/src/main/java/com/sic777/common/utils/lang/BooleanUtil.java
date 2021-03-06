package com.sic777.common.utils.lang;



/**
 * <p>布尔工具类
 *
 * @author sic777
 * @since 0.0.1
 */
public class BooleanUtil {
    private BooleanUtil() {
    }

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
