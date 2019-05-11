package com.sic777.common.thread;

import com.sic777.common.queue.AbstractQueueSic777;
import com.sic777.common.spi.QueueProcessSpi;
import com.sic777.common.spi.manager.SpiManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.AbstractQueue;

/**
 * <p>
 *
 * @author sic777
 * @version 0.0.1
 * @since 0.0.1
 */
public class QueueThread extends Thread {
    private final int index;
    private final String uniqueName;
    private final AbstractQueue<? extends AbstractQueueSic777> abstractQueue;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final QueueProcessSpi[] processSpis;

    public QueueThread(int index, String uniqueName, AbstractQueue<AbstractQueueSic777> abstractQueue) {
        this.index = index;
        this.uniqueName = uniqueName;
        this.abstractQueue = abstractQueue;
        this.processSpis = SpiManager.instance().getQueueProcessSpi();
    }

    public QueueThread(int index, String uniqueName, AbstractQueue<AbstractQueueSic777> abstractQueue, QueueProcessSpi... processSpis) {
        this.index = index;
        this.uniqueName = uniqueName;
        this.abstractQueue = abstractQueue;
        this.processSpis = processSpis;
    }

    @Override
    public void run() {
        for (; ; ) {
            try {
                while (abstractQueue.peek() != null) {
                    try {
                        AbstractQueueSic777 queue = abstractQueue.poll();
                        for (int i = 0, len = processSpis.length; i < len; i++) {
                            QueueProcessSpi spi = processSpis[i];
                            if (queue != null) {
                                if (spi.key().equals(queue.getSpiKey())) {
                                    spi.process(queue);
                                }
                            }
                        }
                    } catch (Throwable e) {
                        logger.error(uniqueName + "-" + index + "-QueueThread.run.sleep:", e);
                    }
                }
                Thread.sleep(10);
            } catch (Throwable e) {
                logger.error(uniqueName + "-" + index + "-QueueThread.run:", e);
            }
        }
    }
}
