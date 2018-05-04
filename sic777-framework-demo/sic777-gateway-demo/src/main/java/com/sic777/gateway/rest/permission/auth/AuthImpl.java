package com.sic777.gateway.rest.permission.auth;

import com.alibaba.fastjson.JSONObject;
import com.sic777.gateway.rest.permission.RestPermissionExtend;
import com.sic777.microserver.constants.MicroConstants;
import com.sic777.microserver.permission.AccessToken;
import com.sic777.microserver.spi.auth.IAuthSPI;
import org.springframework.stereotype.Component;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-05-04
 */
@Component
public class AuthImpl implements IAuthSPI {
    @Override
    public AccessToken parseToken(String accessToken) throws Exception {
        System.out.println(accessToken);
        JSONObject data = new JSONObject();
        data.put(MicroConstants.PERMISSION_FLAG, RestPermissionExtend.BIZ);
        return new AccessToken(accessToken, data);
    }
}
