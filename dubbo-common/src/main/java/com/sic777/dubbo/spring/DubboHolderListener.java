package com.sic777.dubbo.spring;

import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-06-04
 */
public class DubboHolderListener<T extends ApplicationEvent> implements ApplicationListener<T> {
    private static Thread holdThread;
    private static Boolean running;

    public DubboHolderListener() {
    }

    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationPreparedEvent) {
            if (running == Boolean.FALSE) {
                running = Boolean.TRUE;
            }

            if (holdThread == null) {
                holdThread = new Thread(new Runnable() {
                    public void run() {
                        System.out.println(Thread.currentThread().getName());

                        while (running && !Thread.currentThread().isInterrupted()) {
                            try {
                                Thread.sleep(2000L);
                            } catch (InterruptedException e) {
                                ;
                            }
                        }

                    }
                }, "Dubbo-Holder");
                holdThread.setDaemon(false);
                holdThread.start();
            }
        }

        if (event instanceof ContextClosedEvent) {
            running = Boolean.FALSE;
            if (null != holdThread) {
                holdThread.interrupt();
                holdThread = null;
            }
        }

    }

    static {
        running = Boolean.FALSE;
    }
}

