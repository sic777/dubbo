package com.sic777.queue;

import com.sic777.common.queue.AbstractQueueSic777;
import com.sic777.common.spi.QueueProcessSpi;

import java.util.concurrent.atomic.AtomicLong;

/**
 * <p>
 *
 * @author sic777
 * @version 0.0.1
 * @since 0.0.1
 */
public class QueueSpiImpl1 implements QueueProcessSpi {
    private final AtomicLong counter = new AtomicLong(0);

    @Override
    public void process(AbstractQueueSic777 queue) {
        System.out.println("\n负责统计...");
        System.out.println("当前调用次数：" + counter.incrementAndGet());
    }

    @Override
    public Object key() {
        return Constants.COUNTER;
    }
}
