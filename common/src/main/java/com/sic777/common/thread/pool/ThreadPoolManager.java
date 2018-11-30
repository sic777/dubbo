package com.sic777.common.thread.pool;

import com.sic777.common.constants.enums.ClassState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * <p>线程池管理器
 *
 * @author sic777
 * @version 0.0.1
 * @since 0.0.1
 */
public class ThreadPoolManager {

    private ThreadPoolManager() {
    }

    private static class ThreadPoolManagerHolder {
        static ThreadPoolManager singleton = new ThreadPoolManager();
    }

    public static ThreadPoolManager instance() {
        return ThreadPoolManagerHolder.singleton;
    }

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 初始化状态
     */
    private final ConcurrentHashMap<String, AtomicReference<ClassState>> stateMap = new ConcurrentHashMap<>();
    /**
     * 线程池
     */
    private final ConcurrentHashMap<String, ExecutorService> threadPool = new ConcurrentHashMap<>();

    /**
     * 初始化可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
     *
     * @param uniqueName 唯一标识，用于标识线程池的用途
     */
    public void initCachedThreadPool(String uniqueName) {
        AtomicReference<ClassState> state = getState(uniqueName);

        if (!state.compareAndSet(ClassState.UNINITIALIZED, ClassState.INITIALIZING)) {
            throw new IllegalStateException("Cache thread pool already initialized,name:" + uniqueName);
        }

        threadPool.putIfAbsent(uniqueName, Executors.newCachedThreadPool());

        stateMap.get(uniqueName).set(ClassState.INITIALIZED);
        logger.info("Cache thread pool initialized,name:" + uniqueName);
    }

    /**
     * 初始化定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
     *
     * @param threads    the number of threads in the pool
     * @param uniqueName 唯一标识，用于标识线程池的用途
     */
    public void initFixedThreadPool(int threads, String uniqueName) {
        AtomicReference<ClassState> state = getState(uniqueName);

        if (!state.compareAndSet(ClassState.UNINITIALIZED, ClassState.INITIALIZING)) {
            throw new IllegalStateException("Fixed thread pool already initialized,name:" + uniqueName);
        }

        threadPool.putIfAbsent(uniqueName, Executors.newFixedThreadPool(threads));

        stateMap.get(uniqueName).set(ClassState.INITIALIZED);
        logger.info("Fixed thread pool initialized,name:" + uniqueName);
    }

    /**
     * 初始化单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
     *
     * @param uniqueName 唯一标识，用于标识线程池的用途
     */
    public void initSingleThreadExecutor(String uniqueName) {
        AtomicReference<ClassState> state = getState(uniqueName);

        if (!state.compareAndSet(ClassState.UNINITIALIZED, ClassState.INITIALIZING)) {
            throw new IllegalStateException("Single thread pool already initialized,name:" + uniqueName);
        }

        threadPool.putIfAbsent(uniqueName, Executors.newSingleThreadExecutor());

        stateMap.get(uniqueName).set(ClassState.INITIALIZED);
        logger.info("Single thread pool initialized,name:" + uniqueName);
    }

    /**
     * 初始化定长线程池，支持定时及周期性任务执行。
     *
     * @param corePoolSize the number of threads to keep in the pool,even if they are idle
     * @param uniqueName   唯一标识，用于标识线程池的用途
     */
    public void initScheduledThreadPool(int corePoolSize, String uniqueName) {
        AtomicReference<ClassState> state = getState(uniqueName);

        if (!state.compareAndSet(ClassState.UNINITIALIZED, ClassState.INITIALIZING)) {
            throw new IllegalStateException("Scheduled thread pool already initialized,name:" + uniqueName);
        }

        threadPool.putIfAbsent(uniqueName, Executors.newScheduledThreadPool(corePoolSize));

        stateMap.get(uniqueName).set(ClassState.INITIALIZED);
        logger.info("Scheduled thread pool initialized,name:" + uniqueName);
    }

    /**
     * 获取可缓存线程池
     *
     * @param uniqueName 唯一标识，用于标识线程池的用途
     * @return 可缓存线程池
     */
    public ExecutorService getCachedThreadPool(String uniqueName) {
        AtomicReference<ClassState> state = getState(uniqueName);

        if (state.get() != ClassState.INITIALIZED) {
            throw new IllegalStateException("Cache thread pool uninitialized,name:" + uniqueName);
        }
        return threadPool.get(uniqueName);
    }

    /**
     * 获取定长线程池
     *
     * @param uniqueName 唯一标识，用于标识线程池的用途
     * @return 定长线程池
     */
    public ExecutorService getFixedThreadPool(String uniqueName) {
        AtomicReference<ClassState> state = getState(uniqueName);

        if (state.get() != ClassState.INITIALIZED) {
            throw new IllegalStateException("Fixed thread pool uninitialized,name:" + uniqueName);
        }
        return threadPool.get(uniqueName);
    }

    /**
     * 获取单线程化的线程池
     *
     * @param uniqueName 唯一标识，用于标识线程池的用途
     * @return 单线程化的线程池
     */
    public ExecutorService getSingleThreadExecutor(String uniqueName) {
        AtomicReference<ClassState> state = getState(uniqueName);

        if (state.get() != ClassState.INITIALIZED) {
            throw new IllegalStateException("Single thread executor uninitialized,name:" + uniqueName);
        }
        return threadPool.get(uniqueName);
    }

    /**
     * 获取定时任务线程池
     *
     * @param uniqueName 唯一标识，用于标识线程池的用途
     * @return 固定任务线程池
     */
    public ScheduledExecutorService getScheduledThreadPool(String uniqueName) {
        AtomicReference<ClassState> state = getState(uniqueName);
        if (state.get() != ClassState.INITIALIZED) {
            throw new IllegalStateException("Scheduled thread pool uninitialized,name:" + uniqueName);
        }
        return (ScheduledExecutorService) threadPool.get(uniqueName);
    }

    /**
     * 获取类的初始化状态
     *
     * @param uniqueName 唯一标识
     * @return
     */
    private AtomicReference<ClassState> getState(String uniqueName) {
        stateMap.putIfAbsent(uniqueName, new AtomicReference<>(ClassState.UNINITIALIZED));
        return stateMap.get(uniqueName);
    }
}
