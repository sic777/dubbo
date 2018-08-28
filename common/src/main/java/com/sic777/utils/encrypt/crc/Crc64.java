package com.sic777.utils.encrypt.crc;
/**
 * <p>
 *
 * @author sic777
 * @since 0.0.1
 */
class Crc64 {
    static AlgoParams Crc64 = new AlgoParams("CRC-64", 64, 0x42F0E1EBA9EA3693L, 0x00000000L, false, false, 0x00000000L, 0x6C40DF5F0B497347L);
    static AlgoParams Crc64We = new AlgoParams("CRC-64/WE", 64, 0x42F0E1EBA9EA3693L, 0xFFFFFFFFFFFFFFFFL, false, false, 0xFFFFFFFFFFFFFFFFL, 0x62EC59E3F1A4F00AL);
    static AlgoParams Crc64Xz = new AlgoParams("CRC-64/XZ", 64, 0x42F0E1EBA9EA3693L, 0xFFFFFFFFFFFFFFFFL, true, true, 0xFFFFFFFFFFFFFFFFL, 0x995DC9BBDF1939FAL);


    static final AlgoParams[] Params = new AlgoParams[]{
            Crc64, Crc64We, Crc64Xz
    };
}
