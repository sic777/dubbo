package com.sic777.common.utils.random;

import java.util.Random;
import java.util.UUID;

/**
 * <p>
 *
 * @author sic777
 * @version 0.0.1
 * @since 0.0.1
 */
public abstract class ToolRandoms {


    private static final Random random = new Random();

    private static final char authCode[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'G', 'K', 'M', 'N', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'a', 'c', 'd', 'e', 'f', 'g', 'h', 'k', 'm', 'n', 'p', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', '3', '4', '5', '7', '8'};

    private static final char authNumCode[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    private static final int numLength = authNumCode.length;

    private static char getAuthNumCodeChar() {
        return authNumCode[number(0, numLength)];
    }

    public static String getAuthCode(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(authCode[number(0, length)]);
        }
        return sb.toString();
    }

    public static String getAuthNumCode(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(authNumCode[number(0, length)]);
        }
        return sb.toString();
    }

    public static String getUuid(boolean is32bit) {
        String uuid = UUID.randomUUID().toString();
        if (is32bit) {
            return uuid.replace("-", "");
        }
        return uuid;
    }

    public static int number(int number) {
        return random.nextInt(number);
    }


    public static int[] getRandomRgb() {
        int[] rgb = new int[3];
        for (int i = 0; i < 3; i++) {
            rgb[i] = random.nextInt(255);
        }
        return rgb;
    }

    public static String getRandomNum(int len) {
        String sRand = "";
        for (int i = 0; i < len; i++) {
            char tmp = getAuthNumCodeChar();
            sRand += tmp;
        }
        return sRand;
    }

    public static double getRandomDouble(double min, double max, int scl) {
        int pow = (int) Math.pow(10, scl);//指定小数位
        double one = Math.floor((Math.random() * (max - min) + min) * pow) / pow;
        return one;
    }

    private static int number(int min, int max) {
        return min + random.nextInt(max - min);
    }

}
