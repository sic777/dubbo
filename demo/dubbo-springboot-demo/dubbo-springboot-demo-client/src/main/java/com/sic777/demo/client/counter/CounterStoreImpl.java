package com.sic777.demo.client.counter;

import com.sic777.restful.base.counter.ICounterStoreSPI;
import com.sic777.restful.base.counter.RestfulCounter;

import java.util.List;

/**
 * <p>
 *
 * @author sic777
 * @version 0.0.1
 * @since 0.0.1
 */
public class CounterStoreImpl implements ICounterStoreSPI {
    @Override
    public void store(List<RestfulCounter> counters) {
        System.out.println(counters);
    }
}
