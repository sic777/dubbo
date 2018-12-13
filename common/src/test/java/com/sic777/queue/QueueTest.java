package com.sic777.queue;

import com.sic777.common.enums.QueueType;
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
        String tj_name = "统计";
        QueueThreadPoolManager.instance().init(4, tj_name, QueueType.CONCURRENT_LINKED_QUEUE, null);

        String lj_name = "逻辑";
        QueueThreadPoolManager.instance().init(4, lj_name, QueueType.CONCURRENT_LINKED_QUEUE, null);

        for (int i = 0; i < 100; i++) {
            Sic777Queue q_logic = new Sic777Queue(Constants.LOGIC, i, "sic777_" + i);
            QueueThreadPoolManager.instance().offer(lj_name, i, q_logic);

            Sic777Queue q_counter = new Sic777Queue(Constants.COUNTER, i, "sic777_" + i);
            QueueThreadPoolManager.instance().offer(tj_name, i, q_counter);
        }
    }
}