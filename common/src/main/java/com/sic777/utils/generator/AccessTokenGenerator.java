package com.sic777.utils.generator;

import com.sic777.utils.encrypt.base64.Base64Utils;
import com.sic777.utils.encrypt.sha1.SHA1Tool;

import java.security.NoSuchAlgorithmException;

/**
 * <p>accessToken生成器</p>
 *
 * @author Zhengzhenxie<br>
 *         <br>2018-01-01
 * @version v1.0
 * @since 1.7
 */
public class AccessTokenGenerator {
    /**
     * 密钥
     */
    private static final String KEY = "sic777.server.789456800$%&^!@#$~!@#";
    private static final AccessTokenGenerator singleton = new AccessTokenGenerator();

    public static final AccessTokenGenerator instance() {
        return singleton;
    }

    private AccessTokenGenerator() {
    }

    public String next(String customData) throws NoSuchAlgorithmException {
        return Base64Utils.encode(SHA1Tool.SHA256(customData + KEY + UUIDGenerator.instance().next()).getBytes());
    }
}
