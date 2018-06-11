package com.sic777.dubbo.consumer;

import com.sic777.common.constants.BaseConstant;
import com.sic777.common.system.CurrentEnvironment;
import com.sic777.common.laucher.processor.IStarterProcessor;
import com.sic777.utils.system.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

import java.util.concurrent.CountDownLatch;

/**
 * <p>
 * 服务消费者启动类</br>
 * 开发者可以实现org.springframework.boot.CommandLineRunner接口来做一些初始化工作(调用时机为容器启动之后),
 * 该接口可以使用注解org.springframework.core.annotation.Order,
 * 在拥有多个初始化数据操作的时候设置初始化的顺序
 * (请从下标10开始，0-9为默认占用，值越小越先执行)
 * </p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-04-23
 */
@Order(0)
@SpringBootApplication(scanBasePackages = BaseConstant.BASE_SPRING_SCAN_PACKAGE)
public class DubboConsumerLauncher implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(DubboConsumerLauncher.class);

    @Bean
    public CountDownLatch closeLatch() {
        return new CountDownLatch(1);
    }

    public static void start() {
        start(null, null);
    }

    public static void start(IStarterProcessor process, Thread hook) {
        try {
            if (null != process) {
                process.before();
            }
            ApplicationContext ctx = new SpringApplicationBuilder()
                    .sources(DubboConsumerLauncher.class)
                    .web(true)
                    .run();
            if (null != hook) {
                Runtime.getRuntime().addShutdownHook(hook);
            }
            if (null != process) {
                process.after();
            }
            logger.info(String.format("jar version: '%s', environment:'%s'", Version.getVersion(), CurrentEnvironment.instance().getEnvironment().getEnvironment()));
            CountDownLatch closeLatch = ctx.getBean(CountDownLatch.class);
            closeLatch.await();
        } catch (Exception e) {
            logger.error("", e);
            System.exit(-1);
        }
    }

    @Override
    public void run(String... strings) {
        logger.info("The program is started successfully, and initialization is to be executed ...");
    }
}
