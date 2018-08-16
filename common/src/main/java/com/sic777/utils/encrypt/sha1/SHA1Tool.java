package com.sic777.utils.encrypt.sha1;

import com.sic777.utils.StringUtil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-02-11 10:58
 */
public class SHA1Tool {
    /**
     * sha1加密
     *
     * @param sourceString
     * @return
     * @throws NoSuchAlgorithmException
     */
    public final static String SHA1(String sourceString) throws NoSuchAlgorithmException {
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


    public final static String SHA256(String sourceString) throws NoSuchAlgorithmException {
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
