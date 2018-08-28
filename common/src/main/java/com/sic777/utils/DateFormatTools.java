package com.sic777.utils;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * <p>日期格式化工具
 *
 * @author sic777
 * @since 0.0.1
 */
public class DateFormatTools {

    private DateFormatTools() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 根据格式化方式获取日期字符串
     *
     * @param date   日期对象
     * @param format 格式化
     * @return
     */
    public static String funcGetDateWithFormat(Date date, String format) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 根据格式化方式获取日期对象
     *
     * @param date   日期字符串
     * @param format 格式化
     * @return
     */
    public static Date funcGetDateWithFormat(String date, String format) {
        if (null == date) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        try {
            return dateFormat.parse(date);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * yyyy-MM-dd'T'HH:mm:ss.SS'Z'
     *
     * @param date 日期对象
     * @return
     */
    public static String funcGetDate(Date date) {
        return funcGetDateWithFormat(date, "yyyy-MM-dd'T'HH:mm:ss.SS'Z'");
    }

    /**
     * yyyy-MM-dd'T'HH:mm:ss.SS'Z'
     *
     * @param date 日期字符串
     * @return
     */
    public static Date funcGetDate(String date) {
        return funcGetDateWithFormat(date, "yyyy-MM-dd'T'HH:mm:ss.SS'Z'");
    }

    /**
     * yyyy-MM-dd
     *
     * @param date 日期对象
     * @return
     */
    public static String funcGetDay(Date date) {
        return funcGetDateWithFormat(date, "yyyy-MM-dd");
    }

    /**
     * yyyy-MM-dd
     *
     * @param date 日期字符串
     * @return
     */
    public static Date funcGetDay(String date) {
        return funcGetDateWithFormat(date, "yyyy-MM-dd");
    }

    /**
     * 计算相差天数
     *
     * @param begin 开始日期
     * @param end   结束日期
     * @return
     * @throws Exception
     */
    public static long differenceDay(Date begin, Date end) throws Exception {
        long df = end.getTime() - begin.getTime();
        if (df <= 0) {
            throw new Exception("format error");
        }
        return df / (1000 * 60 * 60 * 24);
    }

    /**
     * 计算相差小时数
     *
     * @param begin 开始日期
     * @param end   结束日期
     * @return
     * @throws Exception
     */
    public static int differenceHours(Date begin, Date end) throws Exception {
        long df = end.getTime() - begin.getTime();
        if (df <= 0) {
            throw new Exception("format error");
        }
        return (int) (df / (1000 * 60 * 60));
    }

    /**
     * 将小时数格式文本化：xx天xx小时 / xx小时
     *
     * @param hours 小时数
     * @return
     */
    public static String formatHoursToText(int hours) {
        return hours < 24 ? hours + "小时" : (hours == 24 ? "1天" : hours / 24 + "天" + hours % 24 + "小时");
    }

}
