package com.sic777.restful.base.counter;

import com.sic777.restful.base.constants.ConfigureConstants;
import com.sic777.utils.container.ContainerGetter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * <p>
 *
 * @author sic777
 * @version 0.0.1
 * @since 0.0.1
 */
public class RestfulCounterManager {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static RestfulCounterManager singleton = new RestfulCounterManager();

    public static RestfulCounterManager instance() {
        return singleton;
    }

    private ServiceLoader<ICounterStoreSPI> storeSpi = ServiceLoader.load(ICounterStoreSPI.class);

    private RestfulCounterManager() {
        //TODO 线程池队列处理（优雅关闭时可以继续统计），优先级最低。
        Iterator<ICounterStoreSPI> i = storeSpi.iterator();
        for (int index = 0; i.hasNext(); index++) {
            ICounterStoreSPI spi = i.next();
            Thread th = new Thread(() -> {
                try {
                    for (; ; ) {
                        List<RestfulCounter> counters = new ArrayList<>();
                        Set<String> keys = counter.keySet();
                        for (String key : keys) {
                            String[] ss = key.split("_");
                            counters.add(new RestfulCounter(ss[0], ss[1], counter.get(key).get()));
                        }
                        spi.store(counters);
                        Thread.sleep(ConfigureConstants.COUNTER_CYCLE);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            th.setName("restful-counter-" + index);
            th.start();
        }
    }

    private Map<String, AtomicLong> counter = ContainerGetter.concurHashMap();

    private String getKey(String uri, String method) {
        StringBuffer sb = new StringBuffer();
        return sb.append(uri).append("_").append(method).toString();
    }

    /**
     * URI自增统计
     *
     * @param uri
     * @param method
     */
    public void inc(String uri, String method) {
        String key = getKey(uri, method);
        AtomicLong c = counter.get(key);
        if (null == c) {
            c = new AtomicLong();
            counter.putIfAbsent(key, c);
        }
        long n = c.incrementAndGet();
        logger.debug(String.format("[counter] uri:%s,type:%s,count:%s", uri, method, n));
    }

    /**
     * 获取当前URI调用的次数
     *
     * @param uri
     * @param method
     * @return
     */
    public long get(String uri, String method) {
        String key = getKey(uri, method);
        AtomicLong c = counter.get(key);
        return null != c ? c.get() : 0;
    }
}
