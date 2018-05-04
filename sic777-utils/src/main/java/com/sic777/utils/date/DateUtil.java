package com.sic777.utils.date;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-05-04
 */
public class DateUtil {

    /**
     * 日期转换
     *
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        if (date == null) {
            return null;
        }
        String format = "yyyy-MM-dd'T'HH:mm:ss.SS'Z'";
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }
}
