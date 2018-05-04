package com.sic777.utils.string;

/**
 * Created by Zhengzhenxie on 2017/9/12.
 */
public class StringUtil {
    /**
     * parse object to string
     *
     * @param obj
     * @return
     */
    public static String getString(Object obj) {
        if (null != obj) {
            return String.valueOf(obj);
        }
        return null;
    }

    /**
     * parse string to int
     *
     * @param str
     * @return
     */
    public static int getInt(String str) {
        if (null != str) {
            return Integer.parseInt(str);
        }
        return 0;
    }

    /**
     * parse string to boolean
     *
     * @param str
     * @return
     */
    public static boolean getBoolean(String str) {
        if (null != str) {
            return Boolean.parseBoolean(str);
        }
        return false;
    }

    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return null == str || str.isEmpty();
    }

    /**
     * 判断字符串是否不为空
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return null != str && !str.isEmpty();
    }


    /**
     * 判断字符串是否为Null
     *
     * @param str
     * @return
     */
    public static boolean isNull(String str) {
        return null == str;
    }

    /**
     * 判断字符串是否不为Null
     *
     * @param str
     * @return
     */
    public static boolean isNotNull(String str) {
        return null != str;
    }


    /**
     * 判断首字母是否大写
     *
     * @param str
     * @return
     * @throws Exception
     */
    public static boolean startWithUpperCase(String str) throws Exception {
        int c = (int) str.charAt(0);
        return (int) 'A' <= c && c <= (int) 'Z';
    }

    /**
     * 判断首字母是否小写
     *
     * @param str
     * @return
     * @throws Exception
     */
    public static boolean startWithLowerCase(String str) throws Exception {
        int c = (int) str.charAt(0);
        return (int) 'a' <= c && c <= (int) 'z';
    }


    /**
     * 大写字母变成下划线+小写字母
     *
     * @param str
     * @return
     */
    public static String upperToUnderlineLower(String str) {
        if (null == str) {
            return null;
        }
        char[] c = new char[str.length() << 1];
        int i = 0;
        for (char xx : str.toCharArray()) {
            if ((int) 'A' <= (int) xx && (int) xx <= (int) 'Z') {
                c[i++] = '_';
                c[i++] = (char) (xx + 32);
                continue;
            }
            c[i++] = xx;
        }
        return new String(c).trim();
    }

    /**
     * 首字母大写
     *
     * @param str
     * @return
     */
    public static String toFirstUpperCase(String str) throws Exception {
        if (startWithLowerCase(str)) {
            char[] c = str.toCharArray();
            c[0] -= 32;
            return String.valueOf(c);
        }
        return str;
    }

    /**
     * 首字母小写
     *
     * @param str
     * @return
     */
    public static String toFirstLowerCase(String str) throws Exception {
        if (startWithUpperCase(str)) {
            char[] c = str.toCharArray();
            c[0] += 32;
            return String.valueOf(c);
        }
        return str;
    }

    /**
     * 全部转换成小写
     *
     * @param str
     * @return
     * @throws Exception
     */
    public static String toLowerCase(String str) throws Exception {
        return str.toLowerCase();
    }

    /**
     * 全部转换成大写
     *
     * @param str
     * @return
     * @throws Exception
     */
    public static String toUpperCase(String str) throws Exception {
        return str.toUpperCase();
    }
}
