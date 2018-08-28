package com.sic777.utils.generator;

import com.sic777.utils.encrypt.base64.Base64Utils;
import com.sic777.utils.encrypt.sha1.SHA1Tool;

import java.security.NoSuchAlgorithmException;

/**
 * <p>accessToken生成器
 *
 * @author sic777
 * @since 0.0.1
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
