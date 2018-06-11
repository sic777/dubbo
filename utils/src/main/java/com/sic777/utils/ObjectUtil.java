package com.sic777.utils;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-06-05
 */
public class ObjectUtil {
    /**
     * 判断对象是否为Null
     *
     * @param obj
     * @return
     */
    public static boolean isNull(Object obj) {
        return null == obj;
    }

    /**
     * 判断对象是否不为Null
     *
     * @param obj
     * @return
     */
    public static boolean isNotNull(Object obj) {
        return !isNull(obj);
    }


}
