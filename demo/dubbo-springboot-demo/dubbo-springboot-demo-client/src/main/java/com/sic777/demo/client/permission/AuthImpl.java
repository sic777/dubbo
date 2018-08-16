package com.sic777.demo.client.permission;

import com.alibaba.fastjson.JSONObject;
import com.sic777.microservice.spi.auth.IAuthSPI;
import com.sic777.utils.container.tuple.TwoTuple;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-08-16
 */
public class AuthImpl implements IAuthSPI {
    @Override
    public TwoTuple<JSONObject, Integer> parse(String accessToken) throws Exception {
        return null;
    }
}
