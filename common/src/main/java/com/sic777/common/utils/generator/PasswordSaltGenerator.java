package com.sic777.common.utils.generator;

/**
 * <p>
 *
 * @author sic777
 * @version 0.0.1
 * @since 0.0.1
 */
public class PasswordSaltGenerator{
    private static final PasswordSaltGenerator singleton = new PasswordSaltGenerator();

    public static PasswordSaltGenerator instance() {
        return singleton;
    }

    private PasswordSaltGenerator() {
    }

    public String next(String dict) {
        int length = 8;
        String ret;
        int len = dict.length();
        boolean bDone = true;
        do {
            ret = "";
            int count = 0;
            for (int i = 0; i < length; i++) {
                double dblR = Math.random() * len;
                int intR = (int) Math.floor(dblR);
                char c = dict.charAt(intR);
                if (('0' <= c) && (c <= '9')) {
                    count++;
                }
                ret += dict.charAt(intR);
            }
            if (count >= 2) {
                bDone = false;
            }
        } while (bDone);
        return ret;
    }
}
