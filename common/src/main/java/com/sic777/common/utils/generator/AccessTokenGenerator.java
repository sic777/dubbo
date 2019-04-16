package com.sic777.common.utils.generator;

import com.sic777.common.utils.encrypt.base64.Base64Util;
import com.sic777.common.utils.encrypt.sha1.SHA1Tool;
import com.sic777.common.utils.proguard.NoProguard;

import java.security.NoSuchAlgorithmException;

/**
 * <p>accessToken生成器
 *
 * @author sic777
 * @since 0.0.1
 */
@NoProguard
public class AccessTokenGenerator {
    /**
     * 密钥
     */
    private static final AccessTokenGenerator singleton = new AccessTokenGenerator();

    public static final AccessTokenGenerator instance() {
        return singleton;
    }

    private AccessTokenGenerator() {
    }

    public String next(String key, String customData) throws NoSuchAlgorithmException {
        return Base64Util.encode(SHA1Tool.sha256(customData + key + UUIDGenerator.instance().next()).getBytes());
    }
}
