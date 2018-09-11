package com.sic777.demo.client.counter;

import com.sic777.restful.base.counter.ICounterStoreSPI;
import com.sic777.restful.base.counter.UriCounter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *
 * @author sic777
 * @version 0.0.1
 * @since 0.0.1
 */
public class CounterStoreImpl implements ICounterStoreSPI {
    private Map<String, UriCounter> map = new HashMap<>();

    @Override
    public void statistics(List<UriCounter> uriCounters) {
        for (UriCounter uriCounter : uriCounters) {
            String uri = uriCounter.getUri();
            long count = uriCounter.getCount();
            UriCounter c = map.get(uri);
            long historyCount = null != c ? c.getCount() : 0;
            map.put(uri, new UriCounter(uri, count + historyCount));
        }
        System.out.println("远程统计情况：" + map);
    }
}
