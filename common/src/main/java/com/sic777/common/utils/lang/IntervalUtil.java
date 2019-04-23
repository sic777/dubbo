package com.sic777.common.utils.lang;



/**
 * <p>区间运算工具类
 *
 * @author sic777
 * @since 0.0.1
 */
public class IntervalUtil {
    private IntervalUtil() {
    }

    /**
     * 运算符枚举
     */
    public enum Operator {
        /**
         * 大于
         */
        GT,
        /**
         * 大于等于
         */
        GET,
        /**
         * 小于
         */
        LT,
        /**
         * 小于等于
         */
        LET
    }


    /**
     * <p>判断是否为开区间
     * {@code  start < param < end}
     *
     * @param param 参数
     * @param start 小值
     * @param end   大值
     * @return
     */
    public static boolean isOpen(long param, long start, long end) {
        return check(start, end, Operator.GET) && check(param, start, Operator.GT)
                && check(param, end, Operator.LT);
    }


    /**
     * <p>判断是否为闭区间
     * {@code  start <= param <= end}
     *
     * @param param 参数
     * @param start 小值
     * @param end   大值
     * @return
     */
    public static boolean isClose(long param, long start, long end) {
        return check(start, end, Operator.GET) && check(param, start, Operator.GET)
                && check(param, end, Operator.LET);
    }


    /**
     * <p>判断是否满足半开区间
     * {@code  start < param <= end}
     *
     * @param param 参数
     * @param start 小值
     * @param end   大值
     * @return
     */
    public static boolean isCloseEnd(long param, long start, long end) {
        return check(start, end, Operator.GET) && check(param, start, Operator.GT)
                && check(param, end, Operator.LET);
    }


    /**
     * <p>判断是否满足半开区间
     * {@code start <= param < end}
     *
     * @param param 参数
     * @param start 小值
     * @param end   大值
     * @return
     */
    public static boolean isCloseStart(long param, long start, long end) {
        return check(start, end, Operator.GET) && check(param, start, Operator.GET)
                && check(param, end, Operator.LT);
    }

    /**
     * @param param    原始参数
     * @param compare  比对参数
     * @param operator 运算类型
     * @return
     * @throws IllegalArgumentException
     */
    public static boolean check(long param, long compare, Operator operator) throws IllegalArgumentException {
        switch (operator) {
            case GT:
                return param > compare;
            case LT:
                return param < compare;
            case GET:
                return param >= compare;
            case LET:
                return param <= compare;
            default:
                throw new IllegalArgumentException("parameter 'operator' error, plz pass the correct parameters");
        }
    }
}
