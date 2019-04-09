package com.sic777.dubbo.springboot.comsumer.config;

import com.sic777.dubbo.common.constants.DubboConstant;
import com.sic777.dubbo.comsumer.common.config.SuperDubboConsumerConfig;
import com.sic777.dubbo.comsumer.common.config.build.DubboConsumerConfigManager;
import com.sic777.dubbo.springboot.common.config.SpringBootDubboApplicationConfig;
import com.sic777.dubbo.springboot.common.config.SpringBootDubboRegistryConfig;
import org.apache.dubbo.config.ConsumerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-05-31
 */
@Configuration
@ConfigurationProperties(prefix = DubboConstant.CONSUMER_CONFIG_PREFIX)
public class SpringBootDubboConsumerConfig extends SuperDubboConsumerConfig {

    @Autowired
    private SpringBootDubboApplicationConfig dubboApplicationConfig;
    @Autowired
    private SpringBootDubboRegistryConfig dubboRegistryConfig;

    @Bean
    public ConsumerConfig consumerConfig() {
        return DubboConsumerConfigManager.instance().build(dubboApplicationConfig, dubboRegistryConfig, this);
    }
}