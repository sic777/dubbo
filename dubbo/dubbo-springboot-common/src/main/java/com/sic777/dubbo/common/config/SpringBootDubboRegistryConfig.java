package com.sic777.dubbo.common.config;

import com.sic777.dubbo.common.config.build.DubboRegistryConfigManager;
import com.sic777.dubbo.common.constants.DubboConstant;
import com.sic777.dubbo.common.springboot.SpringBeanRegistry;
import com.alibaba.dubbo.config.RegistryConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * <p>注册中心配置</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-04-24
 */
@Configuration
@ConfigurationProperties(prefix = DubboConstant.REGISTRY_CONFIG_PREFIX)
public class SpringBootDubboRegistryConfig extends SuperDubboRegistryConfig {
    @Autowired
    private SpringBeanRegistry springBeanRegistry;

    @Bean
    public Map<String, RegistryConfig> registryConfigMap() {
        Map<String, RegistryConfig> m = DubboRegistryConfigManager.instance().build(this);
        for (Map.Entry<String, RegistryConfig> entry : m.entrySet()) {
            String idValue = entry.getKey();
            RegistryConfig registry = entry.getValue();
            if (!springBeanRegistry.containsBean(idValue)) {
                springBeanRegistry.register(idValue, registry);
            }
        }
        return m;
    }
}
