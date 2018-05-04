package com.sic777.dubbo.provider.spring.context;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * <p>Spring上下文</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-04-25
 */
@Component
public class SpringContext implements ApplicationContextAware {
    public static DefaultListableBeanFactory BEAN_FACTORY;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (null == BEAN_FACTORY) {
            BEAN_FACTORY = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
        }
    }
}
