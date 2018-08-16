package com.sic777.utils.encrypt.aes;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * <p>AES加密工具类</p>
 *
 * @author Zhengzhenxie<br>
 * <br>2017-12-09 17:55
 * @version v1.0
 * @since 2018-03-03 17:15
 */
public class AESTool {
    private AESTool() {
    }

    /**
     * 加密
     *
     * @param content
     * @param password
     * @return
     * @throws Exception
     */
    public static byte[] encrypt(String content, String password) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        // 防止linux下 随机生成key
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(password.getBytes());
        kgen.init(128, secureRandom);
        SecretKey secretKey = kgen.generateKey();
        byte[] enCodeFormat = secretKey.getEncoded();
        SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
        Cipher cipher = Cipher.getInstance("AES");// 创建密码器
        byte[] byteContent = content.getBytes("utf-8");
        cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
        return cipher.doFinal(byteContent);
    }

    /**
     * 解密
     *
     * @param content
     * @param password
     * @return
     * @throws Exception
     */
    public static String decrypt(byte[] content, String password) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        // 防止linux下 随机生成key
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(password.getBytes());
        kgen.init(128, secureRandom);
        SecretKey secretKey = kgen.generateKey();
        byte[] enCodeFormat = secretKey.getEncoded();
        SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
        Cipher cipher = Cipher.getInstance("AES");// 创建密码器
        cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
        byte[] result = cipher.doFinal(content);
        return new String(result);
    }
}
