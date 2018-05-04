package com.sic777.dubbo.provider.spring.register;

import com.sic777.dubbo.provider.spring.context.SpringContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
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

    /**
     * 注册Bean到Spring
     *
     * @param beanName
     * @param bean
     */
    public void register(String beanName, Object bean) {
        Class<?> clz = bean.getClass();
        if (SpringContext.BEAN_FACTORY.containsBeanDefinition(beanName)) {//如果Spring上下文已存在Bean，打印错误日志
            logger.error(String.format("bean name: '%s' is already exists", beanName));
            System.exit(-1);
        } else {//如果不存在，创建
            BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(clz);
            SpringContext.BEAN_FACTORY.registerBeanDefinition(beanName, builder.getBeanDefinition());
            SpringContext.BEAN_FACTORY.registerSingleton(beanName, bean);//注入实例
            logger.info(String.format("register definition bean,class path :'%s', bean name: '%s'", clz.getName(), beanName));
        }
    }
}
