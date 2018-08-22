package com.sic777.utils.encrypt.crc;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-08-08
 */
public class CrcManager {
    private static CrcCalculator calculator = new CrcCalculator(Crc16.Crc16Maxim);

    public static long getCrc16Maxim(byte[] data) {
        return calculator.Calc(data);
    }
}
