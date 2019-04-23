package com.sic777.common.utils.lang;


import java.net.URLDecoder;

/**
 * <p>字符串工具类
 *
 * @author sic777
 * @since 0.0.1
 */
public class StringUtil {
    private StringUtil() {
    }

    /**
     * parse object to string,default is null
     *
     * @param obj Object对象
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
     * @param obj          Object对象
     * @param defaultValue 默认值
     * @return
     */
    public static int getInt(Object obj, int defaultValue) {
        if (null != obj) {
            return Integer.parseInt(obj.toString());
        }
        return defaultValue;
    }

    /**
     * 获取int
     *
     * @param obj
     * @return
     */
    public static Integer getInt(Object obj) {
        return null != obj ? Integer.parseInt(obj.toString()) : null;
    }

    /**
     * parse string to boolean
     *
     * @param obj Object对象
     * @return
     */
    public static boolean getBoolean(Object obj) {
        return null != obj && Boolean.parseBoolean(obj.toString());
    }

    /**
     * 判断字符串是否为空
     *
     * @param obj Object对象
     * @return
     */
    public static boolean isEmpty(Object obj) {
        return isNull(obj) || obj.toString().isEmpty();
    }

    /**
     * 判断字符串是否不为空
     *
     * @param obj Object对象
     * @return
     */
    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    /**
     * 判断对象是否为Null
     *
     * @param obj Object对象
     * @return
     */
    public static boolean isNull(Object obj) {
        return null == obj;
    }

    /**
     * 判断对象是否不为Null
     *
     * @param obj Object对象
     * @return
     */
    public static boolean isNotNull(Object obj) {
        return !isNull(obj);
    }


    /**
     * 判断首字母是否大写
     *
     * @param str 字符串
     * @return
     */
    public static boolean startWithUpperCase(String str) {
        int c = (int) str.charAt(0);
        return (int) 'A' <= c && c <= (int) 'Z';
    }

    /**
     * 判断首字母是否小写
     *
     * @param str 字符串
     * @return
     */
    public static boolean startWithLowerCase(String str) {
        int c = (int) str.charAt(0);
        return (int) 'a' <= c && c <= (int) 'z';
    }

    /**
     * 大写字母变成下划线+小写字母
     *
     * @param str 字符串
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
     * @param str 字符串
     * @return
     */
    public static String toFirstUpperCase(String str) {
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
     * @param str 字符串
     * @return
     */
    public static String toFirstLowerCase(String str) {
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
     * @param str 字符串
     * @return
     */
    public static String toLowerCase(String str) {
        return str.toLowerCase();
    }

    /**
     * 全部转换成大写
     *
     * @param str 字符串
     * @return
     */
    public static String toUpperCase(String str) {
        return str.toUpperCase();
    }


    /**
     * 格式化路径(中文、空格等)
     *
     * @param path 路径
     * @return
     * @throws Exception
     */
    public static String parsePath(String path) throws Exception {
        return URLDecoder.decode(path.replaceAll("%20", ""), "utf-8");
    }


    /**
     * hexString转为byte数组
     *
     * @param hexStr hexString
     * @return
     */
    public static byte[] parseHexStr2ByteArray(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    /**
     * byte数组转成hexString
     *
     * @param bytes byte数组
     * @return
     */
    public static String parseByteArray2HexStr(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            if (((int) bytes[i] & 0xff) < 0x10) {
                sb.append("0");
            }
            sb.append(Long.toString((int) bytes[i] & 0xff, 16));
        }
        return sb.toString();
    }
}
