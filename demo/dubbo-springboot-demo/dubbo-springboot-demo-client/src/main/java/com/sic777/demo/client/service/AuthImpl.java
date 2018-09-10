package com.sic777.demo.client.service;

import com.alibaba.fastjson.JSONObject;
import com.sic777.demo.client.PermissionExtends;
import com.sic777.restful.base.spi.auth.IAuthSPI;
import com.sic777.utils.container.tuple.Tuple;
import com.sic777.utils.container.tuple.TwoTuple;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * @author sic777
 * @version 0.0.1
 * @since 0.0.1
 */
@Component
public class AuthImpl implements IAuthSPI {
    @Override
    public TwoTuple<JSONObject, Integer> parse(String accessToken) throws Exception {
        return Tuple.tuple(new JSONObject(), PermissionExtends.ANYBODY);
    }
}
