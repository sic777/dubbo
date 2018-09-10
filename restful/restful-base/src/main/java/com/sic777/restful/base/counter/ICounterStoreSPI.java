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
     * 持久化
     *
     * @param counters
     */
    void store(List<RestfulCounter> counters);
}
