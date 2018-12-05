package com.sic777.common.utils.encrypt.crc;

/**
 * <p>
 *
 * @author sic777
 * @since 0.0.1
 */
class CrcHelper {

    static long ReverseBits(long ul, int valueLength) {
        long newValue = 0;
        for (int i = valueLength - 1; i >= 0; i--) {
            newValue |= (ul & 1) << i;
            ul >>= 1;
        }
        return newValue;
    }
}
