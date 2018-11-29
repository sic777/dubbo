package com.sic777.common.spi;

import com.sic777.common.queue.AbstractQueueSic777;

/**
 * <p>队列逻辑处理SPI
 *
 * @author sic777
 * @version 0.0.1
 * @since 0.0.1
 */
public interface QueueProcessSpi {
    /**
     * 处理
     *
     * @param queue
     */
    void process(AbstractQueueSic777 queue);
}
