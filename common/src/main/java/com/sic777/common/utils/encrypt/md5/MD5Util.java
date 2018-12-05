package com.sic777.common.utils.encrypt.md5;

import com.sic777.common.utils.lang.StringUtil;
import com.sic777.common.utils.proguard.NoProguard;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <p>MD5工具
 *
 * @author sic777
 * @since 0.0.1
 */
@NoProguard
public class MD5Util {
    private MD5Util() {
    }

    /**
     * 16位md5
     *
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String hexMd5(String string) throws NoSuchAlgorithmException {
        String md5 = md5(string);
        if (md5.length() >= 24) {
            md5 = md5.substring(8, 24);
        }
        return md5;
    }

    /**
     * md5
     *
     * @param string
     * @return String
     * @throws NoSuchAlgorithmException
     */
    public static String md5(String string) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        return StringUtil.parseByteArray2HexStr(md.digest(string.getBytes()));
    }

    /**
     * md5
     *
     * @param bytes
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String md5(byte[] bytes) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        return StringUtil.parseByteArray2HexStr(md.digest(bytes));
    }
}
