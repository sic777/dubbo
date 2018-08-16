package com.sic777.microservice.utils;

import com.sic777.microservice.response.ResponseManager;
import com.sic777.dubbo.exception.RpcException;
import com.sic777.dubbo.exception.RpcExceptionType;
import com.sic777.dubbo.bean.RpcResponse;
import com.sic777.dubbo.bean.RpcResponseStatus;
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
    public static boolean check(RpcResponse<?> rs) {
        boolean isSuccess = rs.getStatus() == RpcResponseStatus.SUCCESS;
        if (!isSuccess) {//Rpc返回失败的时候，检验为何种错误
            RpcException er = rs.getException();
            if (er != null) {
                RpcExceptionType rpcExceptionType = er.getType();
                String msg = er.getMsg();
                switch (rpcExceptionType) {
                    case PARAM_INVALID:
                        ResponseManager.instance().throwRestException(ParamException.PARAM_INVALID(), msg);
                    case INVALID_ACCESS:
                        ResponseManager.instance().throwRestException(AuthenticationException.INVALID_ACCESS(), msg);
                    case RESOURCE_NOT_FOUND:
                        ResponseManager.instance().throwRestException(NotFoundException.RESOURCE_NOT_FOUND(), msg);
                    default:
                        ResponseManager.instance().throwRest503Exception(null);
                }
            }
        }
        return isSuccess;
    }
}
