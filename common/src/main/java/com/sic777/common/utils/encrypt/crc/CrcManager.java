package com.sic777.common.utils.encrypt.crc;


/**
 * <p>Crc管理器
 *
 * @author sic777
 * @since 0.0.1
 */
public class CrcManager {
    private static CrcCalculator calculator = new CrcCalculator(Crc16.Crc16Maxim);

    /**
     * 获取Crc16Maxim的值
     *
     * @param data byte数组
     * @return crc16Maxim的值
     */
    public static long getCrc16Maxim(byte[] data) {
        return calculator.Calc(data);
    }
}
