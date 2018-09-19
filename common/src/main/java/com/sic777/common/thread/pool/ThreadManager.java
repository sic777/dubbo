package com.sic777.common.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * <p>
 *
 * @author sic777
 * @version 0.0.1
 * @since 0.0.1
 */
public class ThreadManager {
    private static ThreadManager singleton = new ThreadManager();

    public static ThreadManager instance() {
        return singleton;
    }

    private ThreadManager() {
    }

    /**
     * 可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
     */
    private ExecutorService cachedThreadPool;
    /**
     * 定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
     */
    private ExecutorService fixedThreadPool;
    /**
     * 单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
     */
    private ExecutorService singleThreadExecutor;
    /**
     * 定长线程池，支持定时及周期性任务执行。
     */
    private ScheduledExecutorService scheduledThreadPool;

    /**
     * 初始化可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
     */
    public void initCachedThreadPool() {
        if (null == cachedThreadPool) {
            cachedThreadPool = Executors.newCachedThreadPool();
        }
    }

    /**
     * 初始化定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
     *
     * @param threads the number of threads in the pool
     */
    public void initFixedThreadPool(int threads) {
        if (null == fixedThreadPool) {
            fixedThreadPool = Executors.newFixedThreadPool(threads);
        }
    }

    /**
     * 初始化单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
     */
    public void initSingleThreadExecutor() {
        if (null == singleThreadExecutor) {
            singleThreadExecutor = Executors.newSingleThreadExecutor();
        }
    }

    /**
     * 初始化定长线程池，支持定时及周期性任务执行。
     *
     * @param corePoolSize the number of threads to keep in the pool,even if they are idle
     */
    public void initScheduledThreadPool(int corePoolSize) {
        if (null == scheduledThreadPool) {
            scheduledThreadPool = Executors.newScheduledThreadPool(corePoolSize);
        }
    }

    public ExecutorService getCachedThreadPool() {
        return cachedThreadPool;
    }

    public ExecutorService getFixedThreadPool() {
        return fixedThreadPool;
    }

    public ExecutorService getSingleThreadExecutor() {
        return singleThreadExecutor;
    }

    public ScheduledExecutorService getScheduledThreadPool() {
        return scheduledThreadPool;
    }
}
