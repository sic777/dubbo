package com.sic777.restful.base.counter;

import java.util.List;

/**
 * <p>
 *
 * @author sic777
 * @version 0.0.1
 * @since 0.0.1
 */
public interface ICounterStoreSPI {
    /**
     * URI统计持久化
     *
     * @param uriCounters
     */
    void statistics(List<UriCounter> uriCounters);
}
