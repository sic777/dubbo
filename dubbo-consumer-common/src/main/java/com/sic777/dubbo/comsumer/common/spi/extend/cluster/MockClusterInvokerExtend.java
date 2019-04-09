package com.sic777.dubbo.comsumer.common.spi.extend.cluster;

import com.sic777.dubbo.bean.RpcResponse;
import com.sic777.dubbo.bean.exception.RpcExceptionType;
import org.apache.dubbo.rpc.*;
import org.apache.dubbo.rpc.cluster.Directory;
import org.apache.dubbo.rpc.RpcResult;
import org.apache.dubbo.rpc.cluster.support.wrapper.MockClusterInvoker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>集群容错扩展</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-06-01
 */
public class MockClusterInvokerExtend<T> extends MockClusterInvoker<T> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public MockClusterInvokerExtend(Directory<T> directory, Invoker<T> invoker) {
        super(directory, invoker);
    }

    @Override
    public Result invoke(Invocation invocation) throws RpcException {
        try {
            return super.invoke(invocation);
        } catch (RpcException e) {
            RpcResponse<?> resp = new RpcResponse<>(RpcExceptionType.CLIENT_EXCEPTION, e.getMessage());
            logger.error("mock error:", resp.getException());
            return new RpcResult(resp);
        }
    }
}
