package com.sic777.restful.base.counter;

import com.sic777.utils.ConfigureManager;
import com.sic777.utils.container.ContainerGetter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/**
 * <p>
 *
 * @author sic777
 * @version 0.0.1
 * @since 0.0.1
 */
public class RestfulCounterManager extends Thread {
    private static RestfulCounterManager singleton = new RestfulCounterManager();

    public static RestfulCounterManager instance() {
        return singleton;
    }

    private RestfulCounterManager() {
    }

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final AtomicBoolean isInit = new AtomicBoolean(false);
    /**
     * 最大支持一百万个队列
     */
    private final static int MAX_QUEUE_SIZE = 1000000;

    /**
     * 统计队列
     */
    private final Queue<String> queue = ContainerGetter.concurLinkedQueue();
    /**
     * 统计器
     */
    private Map<String, AtomicLong> counter = ContainerGetter.concurHashMap();
    /**
     * 默认统计周期（15分钟）
     */
    private final long DEFAULT_CYCLE = 1000 * 60 * 15L;
    /**
     * 统计周期
     */
    private final long COUNTER_CYCLE = ConfigureManager.instance().getLong("counter.cycle", DEFAULT_CYCLE);

    /**
     * 统计数据落地spi
     */
    private final List<ICounterStoreSPI> counterStoreSPIS = ContainerGetter.copyOnWriteArrayList();
    /**
     * 所有uri的spi
     */
    private final IAllUriSPI allUriSPI = ServiceLoader.load(IAllUriSPI.class).iterator().next();
    /**
     * 所有uri集合
     */
    private final Set<String> allUri = ContainerGetter.copyOnWriteArraySet();

    /**
     * 启动
     */
    public void start() {
        if (isInit.compareAndSet(false, true)) {
            Set<String> allUri = allUriSPI.uris();
            for (String s : allUri) {
                counter.putIfAbsent(s, new AtomicLong(0));
                this.allUri.add(s);
            }
            ServiceLoader<ICounterStoreSPI> storeSpi = ServiceLoader.load(ICounterStoreSPI.class);
            Iterator<ICounterStoreSPI> it = storeSpi.iterator();
            for (; it.hasNext(); ) {
                counterStoreSPIS.add(it.next());
            }
            super.start();
            hook();
        }
    }

    /**
     * 统计线程
     */
    @Override
    public void run() {
        long lastUpdate = System.currentTimeMillis();
        for (; ; ) {
            try {
                lastUpdate = statistics(lastUpdate);
                if (queue.peek() != null) {
                    String uri = queue.poll();
                    long n = counter.get(uri).incrementAndGet();
                    logger.debug(String.format("[counter] uri:%s,local cache count:%s", uri, n));
                } else {
                    Thread.sleep(10L);
                }
            } catch (Exception e) {
                logger.error("RestfulCounterManager Thread", e);
            }
        }
    }

    /**
     * 统计
     *
     * @param lastUpdate
     * @return
     */
    private long statistics(long lastUpdate) {
        if (System.currentTimeMillis() - lastUpdate > COUNTER_CYCLE) {
            List<UriCounter> uriCounters = ContainerGetter.arrayList();
            for (String uri : allUri) {
                long c = counter.get(uri).getAndSet(0);
                if (c != 0) {
                    uriCounters.add(new UriCounter(uri, c));
                }
            }
            if (!uriCounters.isEmpty()) {
                for (ICounterStoreSPI spi : counterStoreSPIS) {
                    spi.statistics(uriCounters);
                }
            }
            return System.currentTimeMillis();
        }
        return lastUpdate;
    }

    /**
     * 统计++
     *
     * @param uri
     */
    public void offer(String uri) {
        if (queue.size() >= MAX_QUEUE_SIZE) {
            logger.error("RestfulCounterManager Thread queue limit, max size is " + MAX_QUEUE_SIZE);
            return;
        }
        queue.offer(uri);
    }

    /**
     * JVM钩子
     */
    private void hook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("RestfulCounterManager Thread Shutdown Hook Start...");
            for (; queue.peek() != null; ) {
                String uri = queue.poll();
                long n = counter.get(uri).incrementAndGet();
                logger.info(String.format("[counter] uri:%s,local cache count:%s", uri, n));
            }

            List<UriCounter> uriCounters = ContainerGetter.arrayList();
            for (String uri : allUri) {
                long c = counter.get(uri).get();
                if (c != 0) {
                    uriCounters.add(new UriCounter(uri, c));
                }
            }
            boolean close;
            for (; ; ) {
                if (!uriCounters.isEmpty()) {
                    for (ICounterStoreSPI spi : counterStoreSPIS) {
                        spi.statistics(uriCounters);
                    }
                    close = true;
                } else {
                    close = false;
                }
                if (close) {
                    break;
                }
            }
        }));
    }
}