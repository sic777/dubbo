package com.sic777.dubbo.comsumer.common.spi.extend.filter;

import com.alibaba.dubbo.rpc.*;
import com.sic777.common.utils.proguard.NoProguard;

/**
 * <p>Rpc客户端拦截器</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-05-29
 */
@NoProguard
public class ConsumerExceptionFilterExtend implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        return invoker.invoke(invocation);
    }
}
