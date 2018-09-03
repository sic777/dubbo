package com.sic777.restful.base.utils;

import com.sic777.restful.base.response.ResponseManager;
import com.sic777.dubbo.bean.exception.RpcException;
import com.sic777.dubbo.bean.exception.RpcExceptionType;
import com.sic777.dubbo.bean.RpcResponse;
import com.sic777.dubbo.bean.RpcResponseStatus;
import com.sic777.utils.proguard.NoProguard;

import static com.sic777.common.exception.ExceptionType.ParamExceptionType.*;
import static com.sic777.common.exception.ExceptionType.AuthenticationExceptionType.*;
import static com.sic777.common.exception.ExceptionType.NotFoundExceptionType.*;
/**
 * <p>rpc响应处理工具类</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-06-05
 */
@NoProguard
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
                        ResponseManager.instance().throwRestException(PARAM_INVALID.getId(), msg);
                    case INVALID_ACCESS:
                        ResponseManager.instance().throwRestException(INVALID_ACCESS.getId(), msg);
                    case RESOURCE_NOT_FOUND:
                        ResponseManager.instance().throwRestException(RESOURCE_NOT_FOUND.getId(), msg);
                    default:
                        ResponseManager.instance().throwRest503Exception(null);
                }
            }
        }
        return isSuccess;
    }
}
