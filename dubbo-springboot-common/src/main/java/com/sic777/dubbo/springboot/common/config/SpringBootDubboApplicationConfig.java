package com.sic777.dubbo.springboot.common.config;

import com.sic777.dubbo.common.config.SuperDubboApplicationConfig;
import com.sic777.dubbo.common.config.build.DubboApplicationConfigManager;
import com.sic777.dubbo.common.constants.DubboConstant;
import com.alibaba.dubbo.config.ApplicationConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>应用信息配置</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-04-24
 */
@Configuration
@ConfigurationProperties(prefix = DubboConstant.APPLICATION_CONFIG_PREFIX)
public class SpringBootDubboApplicationConfig extends SuperDubboApplicationConfig {

    @Bean
    public ApplicationConfig applicationConfig() {
        return DubboApplicationConfigManager.instance().build(this);
    }

}
