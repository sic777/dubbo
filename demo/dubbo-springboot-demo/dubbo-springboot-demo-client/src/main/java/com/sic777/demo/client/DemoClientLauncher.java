package com.sic777.demo.client;

import com.sic777.common.system.CurrentEnvironment;
import com.sic777.common.laucher.processor.IStarterProcessor;
import com.sic777.dubbo.consumer.DubboConsumerLauncher;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-06-05
 */
@SpringBootApplication
public class DemoClientLauncher {

    @PostConstruct
    public void init() {
        System.out.println("初始化...");
    }

    public static void main(String[] args) {
        DubboConsumerLauncher.start(new IStarterProcessor() {
            @Override
            public void before() {
                System.out.println("Spring容器启动前");
                CurrentEnvironment.instance().init();
            }

            @Override
            public void after() {
                System.out.println("Spring容器启动后");

            }
        }, new Thread() {
            @Override
            public void run() {
                System.out.println("优雅关闭处理");
            }
        });
    }
}
