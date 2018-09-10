package com.sic777.demo.client.counter;

import com.sic777.restful.base.counter.ICounterStoreSPI;

/**
 * <p>
 *
 * @author sic777
 * @version 0.0.1
 * @since 0.0.1
 */
public class CounterStoreImpl implements ICounterStoreSPI {
    @Override
    public void inc(String uri, String method) {
        System.out.println(uri + ":" + method);
    }
}
