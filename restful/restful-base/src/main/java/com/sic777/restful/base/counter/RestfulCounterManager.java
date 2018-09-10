package com.sic777.restful.base.counter;

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

    private final ICounterStoreSPI spi;

    private RestfulCounterManager() {
        ServiceLoader<ICounterStoreSPI> storeSpi = ServiceLoader.load(ICounterStoreSPI.class);
        Iterator<ICounterStoreSPI> i = storeSpi.iterator();
        if (i.hasNext()) {//只取第一个实现类
            spi = i.next();
        } else {
            spi = null;
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
        logger.debug(String.format("[counter] uri:%s,type:%s,local count:%s", uri, method, n));
        //inc
        spi.inc(uri, method);
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
