package com.sic777.utils.encrypt.md5;

import com.sic777.utils.StringUtil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <p>MD5工具
 *
 * @author sic777
 * @since 0.0.1
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
            return StringUtil.byteArrayToHexString(md.digest(string.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public static String MD5(byte[] bytes) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return StringUtil.byteArrayToHexString(md.digest(bytes));
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}
