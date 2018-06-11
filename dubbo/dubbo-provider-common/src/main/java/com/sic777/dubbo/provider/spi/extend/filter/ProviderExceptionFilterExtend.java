package com.sic777.dubbo.provider.spi.extend.filter;

import com.alibaba.dubbo.rpc.*;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.alibaba.fastjson.JSON;
import com.sic777.dubbo.exception.RpcExceptionType;
import com.sic777.dubbo.bean.RpcResponse;
import com.sic777.utils.system.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Rpc服务端拦截器:统一异常处理</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-05-29
 */
public class ProviderExceptionFilterExtend implements Filter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        try {
            Result result = invoker.invoke(invocation);
            if (result.hasException() && GenericService.class != invoker.getInterface()) {
                return error(result.getException());
            }
            return result;
        } catch (RpcException e) {
            return error(e);
        }
    }

    /**
     * @param throwable
     * @return
     */
    private RpcResult error(Throwable throwable) {
        logger.error("rpc response error:", throwable);
        RpcContext rpcContext = RpcContext.getContext();
        String sideText = "provider";
        String version = Version.getVersion();
        Object interfaceName = rpcContext.getUrl().getAbsolutePath();
        String remoteAddress = rpcContext.getRemoteAddressString();
        String template = "an exception occurs at '%s' side when the server '%s' is invoked, params: ' interface : %s, method : %s, params : %s, jar version : %s'";
        Object[] params = new Object[]{sideText, remoteAddress, interfaceName, rpcContext.getMethodName(), JSON.toJSONString(rpcContext.getArguments()), version};
        String msg = String.format(template, params);
        return new RpcResult(new RpcResponse<>(RpcExceptionType.SERVICE_EXCEPTION, msg));
    }
}
