package com.sic777.utils.encrypt.sha1;

import com.sic777.utils.StringUtil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <p>SHA1工具
 *
 * @author sic777
 * @since 0.0.1
 */
public class SHA1Tool {
    private SHA1Tool() {
    }

    /**
     * sha1加密
     *
     * @param sourceString
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String SHA1(String sourceString) throws NoSuchAlgorithmException {
        String resultString;
        try {
            resultString = new String(sourceString);
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            resultString = StringUtil.byteArrayToHexString(md.digest(resultString.getBytes()));
        } catch (NoSuchAlgorithmException ex) {
            throw ex;
        }
        return resultString;
    }


    public static String SHA256(String sourceString) throws NoSuchAlgorithmException {
        String resultString;
        try {
            resultString = new String(sourceString);
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            resultString = StringUtil.byteArrayToHexString(md.digest(resultString.getBytes()));
        } catch (NoSuchAlgorithmException ex) {
            throw ex;
        }
        return resultString;
    }
}
