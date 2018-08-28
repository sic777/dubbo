package com.sic777.utils;


/**
 * <p>数值工具类
 *
 * @author sic777
 * @since 0.0.1
 */
public class ValueUtil {
    private ValueUtil() {
    }

    /**
     * <p>判断是否为开区间 start 小于 param 小于 end
     *
     * @param param 参数
     * @param start 小值
     * @param end   大值
     * @return
     */
    public static boolean isIntervalOpen(long param, long start, long end) {
        return isInterval(start, end, Operator.GET) && isInterval(param, start, Operator.GT) && isInterval(param, end, Operator.LT);
    }


    /**
     * <p>判断是否为闭区间 start 小于等于 param 小于等于 end
     *
     * @param param 参数
     * @param start 小值
     * @param end   大值
     * @return
     */
    public static boolean isIntervalClose(long param, long start, long end) {
        return isInterval(start, end, Operator.GET) && isInterval(param, start, Operator.GET) && isInterval(param, end, Operator.LET);
    }


    /**
     * <p>判断是否满足半开区间 start 小于 param 小于等于 end
     *
     * @param param 参数
     * @param start 小值
     * @param end   大值
     * @return
     */
    public static boolean isIntervalCloseEnd(long param, long start, long end) {
        return isInterval(start, end, Operator.GET) && isInterval(param, start, Operator.GT) && isInterval(param, end, Operator.LET);
    }


    /**
     * <p>判断是否满足半开区间 start 小于等于 param 小于 end
     *
     * @param param 参数
     * @param start 小值
     * @param end   大值
     * @return
     */
    public static boolean isIntervalCloseStart(long param, long start, long end) {
        return isInterval(start, end, Operator.GET) && isInterval(param, start, Operator.GET) && isInterval(param, end, Operator.LT);
    }

    /**
     * @param param    原始参数
     * @param compare  比对参数
     * @param operator 运算类型
     * @return
     */
    public static boolean isInterval(long param, long compare, Operator operator) {
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
                return false;
        }
    }
}
