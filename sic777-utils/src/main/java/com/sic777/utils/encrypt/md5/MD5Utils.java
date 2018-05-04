package com.sic777.utils.encrypt.md5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <p>MD5加密</p>
 *
 * @author Zhengzhenxie<br>
 *         <br>2017-12-09 17:55
 * @version v1.0
 * @since 2018-03-03 17:15
 */
public class MD5Utils {

    /**
     * 16位md5
     *
     * @return
     */
    public static String hexMD5(String string) {
        String md5 = MD5(string);
        if (null != md5 && md5.length() >= 24) {
            md5 = md5.substring(8, 24);
        }
        return md5;
    }

    /**
     * MD5方法
     *
     * @param string
     * @return String
     */
    public static String MD5(String string) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return byteArrayToHexString(md.digest(string.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public static String MD5(byte[] bytes) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return byteArrayToHexString(md.digest(bytes));
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    private static String byteArrayToHexString(byte[] bytes) {
        StringBuffer buf = new StringBuffer(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            if (((int) bytes[i] & 0xff) < 0x10) {
                buf.append("0");
            }
            buf.append(Long.toString((int) bytes[i] & 0xff, 16));
        }
        return buf.toString();
    }
}
