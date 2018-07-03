package com.sic777.microservice.utils;

import com.sic777.dubbo.exception.RpcException;
import com.sic777.dubbo.exception.RpcExceptionType;
import com.sic777.dubbo.bean.RpcResponse;
import com.sic777.dubbo.bean.RpcResponseStatus;
import com.sic777.microservice.response.ResponseManager;
import com.sic777.microservice.response.exception.error.AuthenticationException;
import com.sic777.microservice.response.exception.error.NotFoundException;
import com.sic777.microservice.response.exception.error.ParamException;

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
                    ResponseManager.instance().throwRest503Exception(null);
                case PARAM_INVALID:
                    ResponseManager.instance().throwRestException(ParamException.PARAM_INVALID(), msg);
                case INVALID_ACCESS:
                    ResponseManager.instance().throwRestException(AuthenticationException.INVALID_ACCESS(), msg);
                case CLIENT_EXCEPTION:
                    ResponseManager.instance().throwRest503Exception(null);
                case SERVICE_EXCEPTION:
                    ResponseManager.instance().throwRest503Exception(null);
                case RESOURCE_NOT_FOUND:
                    ResponseManager.instance().throwRestException(NotFoundException.RESOURCE_NOT_FOUND(), msg);
                default:
                    ResponseManager.instance().throwRest503Exception(null);
            }
        }
    }
}
