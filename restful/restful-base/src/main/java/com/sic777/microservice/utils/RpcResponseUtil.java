package com.sic777.microservice.utils;

import com.sic777.dubbo.exception.RpcException;
import com.sic777.dubbo.exception.RpcExceptionType;
import com.sic777.dubbo.bean.RpcResponse;
import com.sic777.dubbo.bean.RpcResponseStatus;
import com.sic777.microservice.exception.Rest400Exception;
import com.sic777.microservice.exception.Rest403Exception;
import com.sic777.microservice.exception.Rest404Exception;
import com.sic777.microservice.exception.Rest503Exception;
import com.sic777.microservice.exception.error.AuthenticationException;
import com.sic777.microservice.exception.error.NotFoundException;
import com.sic777.microservice.exception.error.ParamException;

/**
 * <p>rpc响应处理工具类</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-06-05
 */
public class RpcResponseUtil {
    /**
     * 检验rpc响应是否存在异常
     *
     * @param rs
     */
    public static void check(RpcResponse<?> rs) {
        if (rs.getStatus() == RpcResponseStatus.FAILURE) {
            RpcException er = rs.getException();
            RpcExceptionType rpcExceptionType = er.getType();
            String msg = er.getMsg();
            switch (rpcExceptionType) {
                case UNKNOWN:
                    throw new Rest503Exception(null, false);
                case PARAM_INVALID:
                    throw new Rest400Exception(ParamException.PARAM_INVALID(), false, msg);
                case INVALID_ACCESS:
                    throw new Rest403Exception(AuthenticationException.INVALID_ACCESS(), false, msg);
                case CLIENT_EXCEPTION:
                    throw new Rest503Exception(null, false);
                case SERVICE_EXCEPTION:
                    throw new Rest503Exception(null, false);
                case RESOURCE_NOT_FOUND:
                    throw new Rest404Exception(NotFoundException.RESOURCE_NOT_FOUND(), false, msg);
                default:
                    throw new Rest503Exception(null, false);
            }
        }
    }
}
