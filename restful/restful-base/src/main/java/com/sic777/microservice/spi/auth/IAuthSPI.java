package com.sic777.microservice.spi.auth;


import com.alibaba.fastjson.JSONObject;
import com.sic777.utils.container.tuple.TwoTuple;

/**
 * <p>鉴权SPI接口，由ISV实现</p>
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
     *
     * @return <accessToken缓存的数据,Rest接口权限值>
     *
     * @throws Exception
     */
    TwoTuple<JSONObject, Integer> parse(String accessToken) throws Exception;
}
