package com.sic777.dubbo.springboot.provider.config;

import com.sic777.dubbo.common.constants.DubboConstant;
import com.sic777.dubbo.provider.common.config.SuperDubboProviderConfig;
import com.sic777.dubbo.provider.common.config.build.DubboProviderConfigManager;
import com.sic777.dubbo.springboot.common.config.SpringBootDubboApplicationConfig;
import com.sic777.dubbo.springboot.common.config.SpringBootDubboRegistryConfig;
import org.apache.dubbo.config.ProviderConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * <p>服务提供者缺省配置</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-04-24
 */
@Configuration
@ConfigurationProperties(prefix = DubboConstant.PROVIDER_CONFIG_PREFIX)
public class SpringBootDubboProviderConfig extends SuperDubboProviderConfig {

    @Autowired
    private SpringBootDubboApplicationConfig dubboApplicationConfig;
    @Autowired
    private SpringBootDubboRegistryConfig dubboRegistryConfig;
    @Autowired
    private SpringBootDubboProtocolConfig dubboProtocolConfig;

    @Bean
    public ProviderConfig providerConfig() {
        return DubboProviderConfigManager.instance().build(dubboApplicationConfig, dubboRegistryConfig, dubboProtocolConfig, this);
    }
}
