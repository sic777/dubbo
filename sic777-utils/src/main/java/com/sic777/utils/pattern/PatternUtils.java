package com.sic777.utils.pattern;

import java.util.regex.Pattern;

/**
 * <p>正则校验工具</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-01-18 10:59
 */
public class PatternUtils {
    /**
     * 校验用户名
     *
     * @param userName
     * @param min
     * @param max
     * @return
     */
    public static boolean validateUserName(String userName, int min, int max) {
        return Pattern.matches("^[A-Za-z0-9_\\u4e00-\\u9fa5]{" + min + "," + max + "}$", userName);
    }

    /**
     * 校验邮箱
     *
     * @param email
     * @param max
     * @return
     */
    public static boolean validateEmail(String email, int max) {
        return max >= 5 && Pattern.matches("^(?=.{5," + max + "}$)[\\s\\S]*@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$", email);
    }

}
