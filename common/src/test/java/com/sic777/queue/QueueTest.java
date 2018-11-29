package com.sic777.queue;

import com.sic777.common.constants.enums.QueueType;
import com.sic777.common.queue.AbstractQueueSic777;
import com.sic777.common.thread.pool.QueueThreadPoolManager;

/**
 * <p>
 *
 * @author sic777
 * @version 0.0.1
 * @since 0.0.1
 */
public class QueueTest {

    public static void main(String[] args) {
        String name1 = "aaa";
        String name2 = "bbb";

        QueueThreadPoolManager.instance().init(4, name1, QueueType.LINKED_BLOCKING_QUEUE, null);
        QueueThreadPoolManager.instance().init(4, name2, QueueType.ARRAY_BLOCKING_QUEUE, 100);

        for (int i = 0; i < 100; i++) {
            QueueThreadPoolManager.instance().offer(name1, new AbstractQueueSic777() {
            });
            QueueThreadPoolManager.instance().offer(name2, new AbstractQueueSic777() {
            });
        }
    }
}
