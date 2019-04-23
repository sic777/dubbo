package com.sic777.dubbo.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 * <p>Spring动态注册器</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-04-25
 */
@Component
public class SpringBeanRegistry {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ConfigurableApplicationContext configurableApplicationContext;

    /**
     * 注册Bean到Spring
     *
     * @param beanName
     * @param bean
     */
    public void register(String beanName, Object bean) {
        ConfigurableListableBeanFactory beanFactory = configurableApplicationContext.getBeanFactory();
        if (beanFactory.containsBeanDefinition(beanName)) {//如果Spring上下文已存在Bean，打印错误日志
            logger.error(String.format("bean name: '%s' is already exists", beanName));
            System.exit(-1);
        } else {//如果不存在，创建
            beanFactory.registerSingleton(beanName, bean);//注入实例
            logger.info(String.format("register definition bean,class path :'%s', bean name: '%s'", bean.getClass().getName(), beanName));
        }
    }

    /**
     * 是否存在该bean
     *
     * @param beanName
     * @return
     */
    public boolean containsBean(String beanName) {
        ConfigurableListableBeanFactory beanFactory = configurableApplicationContext.getBeanFactory();
        return beanFactory.containsBean(beanName);
    }
}
