package com.sic777.common.utils.date;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * <p>日期格式化工具
 *
 * @author sic777
 * @since 0.0.1
 */
public class DateFormatUtil {

    public final static String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SS'Z'";
    public final static String DAY_FORMAT = "yyyy-MM-dd";
    private final static String DAY_FLAG = " Day ";
    private final static String HOUR_FLAG = " Hour ";


    /**
     * 根据格式化方式获取日期字符串
     *
     * @param date   日期对象
     * @param format 格式化
     * @return
     */
    public static String funcGetDateWithFormat(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
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
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * @param date 日期对象
     * @return yyyy-MM-dd'T'HH:mm:ss.SS'Z'
     */
    public static String funcGetDate(Date date) {
        return funcGetDateWithFormat(date, DATE_FORMAT);
    }

    /**
     * @param date 日期字符串,格式：yyyy-MM-dd'T'HH:mm:ss.SS'Z'
     * @return
     */
    public static Date funcGetDate(String date) {
        return funcGetDateWithFormat(date, DATE_FORMAT);
    }

    /**
     * @param date 日期对象
     * @return yyyy-MM-dd
     */
    public static String funcGetDay(Date date) {
        return funcGetDateWithFormat(date, DAY_FORMAT);
    }

    /**
     * @param date 日期字符串
     * @return yyyy-MM-dd
     */
    public static Date funcGetDay(String date) {
        return funcGetDateWithFormat(date, DAY_FORMAT);
    }

    /**
     * 计算相差天数
     *
     * @param begin 开始日期
     * @param end   结束日期
     * @return
     * @throws IllegalArgumentException
     */
    public static long differenceDay(Date begin, Date end) throws IllegalArgumentException {
        long df = end.getTime() - begin.getTime();
        if (df <= 0) {
            throw new IllegalArgumentException("end time must greater than begin time");
        }
        return df / (1000 * 60 * 60 * 24);
    }

    /**
     * 计算相差小时数
     *
     * @param begin 开始日期
     * @param end   结束日期
     * @return
     * @throws IllegalArgumentException
     */
    public static int differenceHours(Date begin, Date end) throws IllegalArgumentException {
        long df = end.getTime() - begin.getTime();
        if (df <= 0) {
            throw new IllegalArgumentException("end time must greater than begin time");
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
        return hours < 24
                ? hours + HOUR_FLAG
                : (hours == 24 ? 1 + DAY_FLAG : hours / 24 + DAY_FLAG + hours % 24 + HOUR_FLAG);
    }
}
