package com.sic777.common.constants.enums;

/**
 * <p>队列枚举
 *
 * @author sic777
 * @version 0.0.1
 * @since 0.0.1
 */
public enum QueueType {
    /**
     * 无界非阻塞队列
     */
    CONCURRENT_LINKED_QUEUE,
    /**
     * 有界阻塞队列
     */
    LINKED_BLOCKING_QUEUE,
    /**
     * 有界阻塞队列
     */
    ARRAY_BLOCKING_QUEUE
}
