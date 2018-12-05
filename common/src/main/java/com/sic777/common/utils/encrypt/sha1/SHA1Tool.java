package com.sic777.common.utils.encrypt.sha1;

import com.sic777.common.utils.lang.StringUtil;
import com.sic777.common.utils.proguard.NoProguard;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <p>SHA1工具
 *
 * @author sic777
 * @since 0.0.1
 */
@NoProguard
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
    public static String sha1(String sourceString) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        return StringUtil.parseByteArray2HexStr(md.digest(sourceString.getBytes()));
    }


    public static String sha256(String sourceString) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return StringUtil.parseByteArray2HexStr(md.digest(sourceString.getBytes()));
    }
}
