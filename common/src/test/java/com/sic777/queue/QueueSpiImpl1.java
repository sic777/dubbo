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
public class QueueSpiImpl1 implements QueueProcessSpi {
    @Override
    public void process(AbstractQueueSic777 queue) {
        System.out.println("1111" + queue);
    }
}
