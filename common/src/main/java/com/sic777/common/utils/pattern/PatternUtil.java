package com.sic777.common.utils.pattern;


import java.util.regex.Pattern;

/**
 * <p>正则校验工具
 *
 * @author sic777
 * @since 0.0.1
 */
public class PatternUtil {
    private PatternUtil() {
    }

    /**
     * 校验用户名
     *
     * @param userName 用户名
     * @param min      最小位数
     * @param max      最大位数
     * @return
     */
    public static boolean validateUserName(String userName, int min, int max) {
        return Pattern.matches("^[A-Za-z0-9_\\u4e00-\\u9fa5]{" + min + "," + max + "}$", userName);
    }

    /**
     * 校验邮箱
     *
     * @param email 邮箱
     * @param max   最大位数
     * @return
     */
    public static boolean validateEmail(String email, int max) {
        return max >= 5 && Pattern.matches("^(?=.{5," + max + "}$)[\\s\\S]*@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$", email);
    }

    /**
     * 校验手机号码
     *
     * @param phoneNumber 手机号码
     * @return
     */
    public static boolean checkMobile(String phoneNumber) {
        String regex = "^1[0-9]{10}$";
        return Pattern.matches(regex, phoneNumber);
    }

    /**
     * 校验验证码
     *
     * @param length       长度
     * @param validateCode 验证码
     * @return
     */
    public static boolean checkValidateCode(int length, String validateCode) {
        String regex = "^[0-9]{" + length + "}$";
        return Pattern.matches(regex, validateCode);
    }

    /**
     * 校验密码
     *
     * @param password 密码
     * @param min      最小位数
     * @param max      最大位数
     * @return
     */
    public static boolean checkPassword(String password, int min, int max) {
        String minS = String.valueOf(min - 1);
        String maxS = String.valueOf(max - 1);
        String regex = "^[a-zA-Z][a-zA-Z0-9_]{" + minS + "," + maxS + "}+$";
        return Pattern.matches(regex, password);
    }
}
