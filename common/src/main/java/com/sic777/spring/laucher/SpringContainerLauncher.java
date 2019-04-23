package com.sic777.spring.laucher;

import com.sic777.spring.constants.SpringBootConstant;
import com.sic777.spring.laucher.processor.IStarterProcessor;
import com.sic777.common.system.CurrentEnvironment;
import com.sic777.common.utils.system.VersionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;


/**
 * <p>Spring容器启动基类
 *
 * @author sic777
 * @since 0.0.1
 */
@Order(0)
@SpringBootApplication(scanBasePackages = {SpringBootConstant.BASE_SPRING_SCAN_PACKAGE, SpringBootConstant.ISV_SPRING_SCAN_PACKAGE})
abstract class SpringContainerLauncher extends AbstractLauncher implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(SpringContainerLauncher.class);

    private static final Set<String> uris = new HashSet<>();

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
            boolean isWeb = isWebEnvironment();
            ApplicationContext ctx = new SpringApplicationBuilder()
                    .sources(SpringContainerLauncher.class)
                    .web(isWeb)
                    .run();
            if (isWeb) {
                setUri(ctx);
            }
            if (null != hook) {
                Runtime.getRuntime().addShutdownHook(hook);
            }
            if (null != process) {
                process.after();
            }
            logger.info(String.format("framework jar version: '%s', environment:'%s'", VersionUtil.getFrameworkVersion(), CurrentEnvironment.instance().getEnvironment().getEnvironment()));
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

    /**
     * 获取所有的uri
     *
     * @return
     */
    public Set<String> getAllUri() {
        return uris;
    }

    /**
     * 获取所有的uri
     *
     * @param ctx
     */
    private void setUri(ApplicationContext ctx) {
        RequestMappingHandlerMapping mapping = ctx.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
        for (RequestMappingInfo info : map.keySet()) {
            Set<String> patterns = info.getPatternsCondition().getPatterns();
            uris.addAll(patterns);
        }
    }
}
