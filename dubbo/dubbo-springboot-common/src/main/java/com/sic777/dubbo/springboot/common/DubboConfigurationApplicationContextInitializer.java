package com.sic777.dubbo.springboot.common;

import com.alibaba.dubbo.config.spring.AnnotationBean;
import com.sic777.utils.proguard.NoProguard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-06-04
 */


@SuppressWarnings("deprecation")
@NoProguard
public class DubboConfigurationApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public DubboConfigurationApplicationContextInitializer() {
    }

    public void initialize(ConfigurableApplicationContext applicationContext) {
        Environment env = applicationContext.getEnvironment();
        String scan = env.getProperty("dubbo.scan");
        if (null == scan) {
            logger.error("plz set dubbo scanner package property: ${dubbo.scan}");
            System.exit(-1);
        }
        AnnotationBean scanner = BeanUtils.instantiate(AnnotationBean.class);
        scanner.setPackage(scan);
        scanner.setApplicationContext(applicationContext);
        applicationContext.addBeanFactoryPostProcessor(scanner);
        applicationContext.getBeanFactory().addBeanPostProcessor(scanner);
        applicationContext.getBeanFactory().registerSingleton("annotationBean", scanner);

    }
}
