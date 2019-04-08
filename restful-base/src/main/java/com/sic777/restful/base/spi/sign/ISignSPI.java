package com.sic777.restful.base.spi.sign;


/**
 * <p>api签名SPI
 *
 * @author sic777
 * @version 0.0.1
 * @since 0.0.1
 */
public interface ISignSPI {
    String salt();

    String secret();
}
