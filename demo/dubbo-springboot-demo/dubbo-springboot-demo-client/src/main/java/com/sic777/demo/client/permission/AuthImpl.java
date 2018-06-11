package com.sic777.demo.client.permission;

import com.alibaba.fastjson.JSONObject;
import com.sic777.microservice.spi.auth.IAuthSPI;
import com.sic777.utils.container.tuple.Tuple;
import com.sic777.utils.container.tuple.TwoTuple;
import org.springframework.stereotype.Component;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-06-05
 */
@Component
public class AuthImpl implements IAuthSPI {
    @Override
    public TwoTuple<JSONObject, Integer> parse(String accessToken) throws Exception {
        JSONObject js = new JSONObject();
        return Tuple.tuple(js, RestPermissionExtend.BIZ);
    }

}
