package com.sic777.dubbo.comsumer.common.spi.extend.cluster;

import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.cluster.Cluster;
import org.apache.dubbo.rpc.cluster.Directory;
import org.apache.dubbo.rpc.cluster.support.wrapper.MockClusterWrapper;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-06-01
 */
public class MockClusterWrapperExtend extends MockClusterWrapper {
    private final Cluster cluster;

    public MockClusterWrapperExtend(Cluster cluster) {
        super(cluster);
        this.cluster = cluster;
    }

    @Override
    public <T> Invoker<T> join(Directory<T> directory) throws RpcException {
        return new MockClusterInvokerExtend<T>(directory, this.cluster.join(directory));
    }
}
