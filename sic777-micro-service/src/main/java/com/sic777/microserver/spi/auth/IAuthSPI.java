package com.sic777.microserver.spi.auth;


import com.sic777.microserver.permission.AccessToken;

/**
 * <p>鉴权SPI接口</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-04-11
 */
public interface IAuthSPI {

    /**
     * 解析token
     *
     * @param accessToken 鉴权凭证
     * @return
     * @throws Exception
     */
    AccessToken parseToken(String accessToken) throws Exception;
}
