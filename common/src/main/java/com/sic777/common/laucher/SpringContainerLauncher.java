package com.sic777.common.laucher;

import com.sic777.common.constants.BaseConstant;
import com.sic777.common.laucher.processor.IStarterProcessor;
import com.sic777.common.system.CurrentEnvironment;
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
 * <p>Spring容器启动基类
 *
 * @author sic777
 * @since 0.0.1
 */
@Order(0)
@SpringBootApplication(scanBasePackages = {BaseConstant.BASE_SPRING_SCAN_PACKAGE, BaseConstant.ISV_SPRING_SCAN_PACKAGE})
abstract class SpringContainerLauncher extends AbstractLauncher implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(SpringContainerLauncher.class);

    @Bean
    public CountDownLatch closeLatch() {
        return new CountDownLatch(1);
    }

    @Override
    void startImpl(IStarterProcessor process, Thread hook) {
        try {
            if (null != process) {
                process.before();
            }
            ApplicationContext ctx = new SpringApplicationBuilder()
                    .sources(SpringContainerLauncher.class)
                    .web(isWebEnvironment())
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
