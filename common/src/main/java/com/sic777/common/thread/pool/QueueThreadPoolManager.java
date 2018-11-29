package com.sic777.common.thread.pool;

import com.sic777.common.constants.enums.QueueType;
import com.sic777.common.queue.AbstractQueueSic777;
import com.sic777.common.thread.QueueThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.AbstractQueue;
import java.util.Random;
import java.util.concurrent.*;

/**
 * <p>
 *
 * @author sic777
 * @version 0.0.1
 * @since 0.0.1
 */
public class QueueThreadPoolManager {
    private static QueueThreadPoolManager singleton = new QueueThreadPoolManager();

    public static QueueThreadPoolManager instance() {
        return singleton;
    }

    private QueueThreadPoolManager() {
    }

    private final ConcurrentHashMap<String, Thread[]> threadMap = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, AbstractQueue<AbstractQueueSic777>[]> queueMap = new ConcurrentHashMap<>();
    private final Random random = new Random();
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 初始化队列
     *
     * @param threadSize 线程数
     * @param uniqueName 唯一标识
     * @param queueType  队列类型
     * @param capacity   队列大小
     *                   在ConcurrentLinkedQueue无效
     *                   在ArrayBlockingQueue中必填,且大于0
     *                   在LinkedBlockingQueue选填,为0或者为null时,默认为Integer.MAX_VALUE
     * @see com.sic777.common.constants.enums.QueueType
     */
    public void init(int threadSize, String uniqueName, QueueType queueType, Integer capacity) {
        if (threadMap.containsKey(uniqueName)) {
            throw new IllegalStateException("queue thread pool already initialized,name:" + uniqueName);
        }
        if (null == queueType) {
            throw new IllegalArgumentException("queue type is null");
        }

        Thread[] threads = new Thread[threadSize];
        AbstractQueue<AbstractQueueSic777>[] abstractQueues = new AbstractQueue[threadSize];

        for (int i = 0; i < threadSize; i++) {
            abstractQueues[i] = createQueue(queueType, null != capacity ? capacity : 0);
            threads[i] = new QueueThread(i, uniqueName, abstractQueues[i]);
            threads[i].start();
        }

        queueMap.putIfAbsent(uniqueName, abstractQueues);
        threadMap.putIfAbsent(uniqueName, threads);

        logger.info("queue thread pool initialized,name:" + uniqueName);
    }

    /**
     * 根据value求模offer
     *
     * @param uniqueName
     * @param value
     * @param queue
     */
    public void offer(String uniqueName, int value, AbstractQueueSic777 queue) {
        AbstractQueue<AbstractQueueSic777>[] queues = queueMap.get(uniqueName);
        if (null == queues) {
            throw new IllegalStateException("queues is null,name:" + uniqueName);
        }
        queues[value % queues.length].offer(queue);
    }

    /**
     * 随机offer
     *
     * @param queue
     */
    public void offer(String uniqueName, AbstractQueueSic777 queue) {
        AbstractQueue<AbstractQueueSic777>[] queues = queueMap.get(uniqueName);
        if (null == queues) {
            throw new IllegalStateException("queues is null,name:" + uniqueName);
        }
        queues[random.nextInt(queues.length)].offer(queue);
    }

    /**
     * 队列长度
     *
     * @param uniqueName
     * @return
     */
    public int queueSize(String uniqueName) {
        AbstractQueue<AbstractQueueSic777>[] queues = queueMap.get(uniqueName);
        int size = 0;
        if (null != queues) {
            for (int i = 0, len = queues.length; i < len; i++) {
                size += queues[i].size();
            }
        }
        return size;
    }

    /**
     * 线程长度
     *
     * @param uniqueName
     * @return
     */
    public int threadSize(String uniqueName) {
        Thread[] threads = threadMap.get(uniqueName);
        return null != threads ? threads.length : 0;
    }

    /**
     * 创建队列对象
     *
     * @param queueType
     * @param capacity  队列大小
     * @return
     */
    private AbstractQueue<AbstractQueueSic777> createQueue(QueueType queueType, Integer capacity) {
        switch (queueType) {
            case CONCURRENT_LINKED_QUEUE:
                return new ConcurrentLinkedQueue<>();
            case LINKED_BLOCKING_QUEUE:
                return new LinkedBlockingQueue<>(null != capacity && capacity != 0 ? capacity : Integer.MAX_VALUE);
            case ARRAY_BLOCKING_QUEUE:
                if (null == capacity || capacity == 0) {
                    throw new IllegalArgumentException("capacity must not be null or zero");
                }
                return new ArrayBlockingQueue<>(capacity);
            default:
                return null;
        }
    }
}
