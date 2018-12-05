package com.sic777.common.utils.pattern;

import com.sic777.common.utils.proguard.NoProguard;

import java.util.regex.Pattern;

/**
 * <p>正则校验工具
 *
 * @author sic777
 * @since 0.0.1
 */
@NoProguard
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
}
