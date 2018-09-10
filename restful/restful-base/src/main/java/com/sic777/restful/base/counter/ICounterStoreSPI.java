package com.sic777.restful.base.counter;

/**
 * <p>
 *
 * @author sic777
 * @version 0.0.1
 * @since 0.0.1
 */
public interface ICounterStoreSPI {
    /**
     * 递增持久化
     *
     * @param uri
     * @param method
     */
    void inc(String uri, String method);
}
