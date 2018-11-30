package com.sic777.queue;

import com.sic777.common.queue.AbstractQueueSic777;
import com.sic777.common.spi.QueueProcessSpi;

/**
 * <p>
 *
 * @author sic777
 * @version 0.0.1
 * @since 0.0.1
 */
public class QueueSpiImpl2 implements QueueProcessSpi {
    @Override
    public void process(AbstractQueueSic777 queue) {
        System.out.println("\n负责逻辑处理...");
        Sic777Queue q = (Sic777Queue) queue;
        System.out.println("接收到的数据" + q);
    }

    @Override
    public Object key() {
        return Constants.LOGIC;
    }
}
