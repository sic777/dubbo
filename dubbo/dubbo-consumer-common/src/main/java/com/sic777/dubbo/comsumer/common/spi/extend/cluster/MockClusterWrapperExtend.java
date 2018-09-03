package com.sic777.dubbo.comsumer.common.spi.extend.cluster;

import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.cluster.Cluster;
import com.alibaba.dubbo.rpc.cluster.Directory;
import com.alibaba.dubbo.rpc.cluster.support.wrapper.MockClusterWrapper;
import com.sic777.utils.proguard.NoProguard;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-06-01
 */
@NoProguard
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
