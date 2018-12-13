package com.sic777.common.spi.manager;

import com.sic777.common.enums.ClassState;
import com.sic777.common.spi.QueueProcessSpi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;
import java.util.concurrent.atomic.AtomicReference;

/**
 * <p>
 *
 * @author sic777
 * @version 0.0.1
 * @since 0.0.1
 */
public class SpiManager {
    private SpiManager() {
    }

    private static class SpiManagerHolder {
        static SpiManager singleton = new SpiManager();
    }

    public static SpiManager instance() {
        return SpiManagerHolder.singleton;
    }

    private final AtomicReference<ClassState> queueProcessSpiInit = new AtomicReference<>(ClassState.UNINITIALIZED);
    private QueueProcessSpi[] queueProcessSpis;

    /**
     * 获取队列处理spi
     *
     * @return
     */
    public synchronized QueueProcessSpi[] getQueueProcessSpi() {
        if (queueProcessSpiInit.compareAndSet(ClassState.UNINITIALIZED, ClassState.INITIALIZING)) {
            ServiceLoader<QueueProcessSpi> loader = ServiceLoader.load(QueueProcessSpi.class);
            Iterator<QueueProcessSpi> it = loader.iterator();
            List<QueueProcessSpi> ls = new ArrayList<>();
            while (it.hasNext()) {
                QueueProcessSpi spi = it.next();
                ls.add(spi);
            }
            queueProcessSpis = ls.toArray(new QueueProcessSpi[]{});
            queueProcessSpiInit.set(ClassState.INITIALIZED);
        }
        if (ClassState.INITIALIZED != queueProcessSpiInit.get()) {
            throw new IllegalStateException("queue process spi uninitialized");
        }
        return queueProcessSpis;
    }
}
