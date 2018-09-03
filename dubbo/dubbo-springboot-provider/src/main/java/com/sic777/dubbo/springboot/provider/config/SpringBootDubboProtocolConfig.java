package com.sic777.dubbo.springboot.provider.config;

import com.sic777.dubbo.common.constants.DubboConstant;
import com.sic777.dubbo.provider.common.config.SuperDubboProtocolConfig;
import com.sic777.dubbo.provider.common.config.build.DubboProtocolConfigManager;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.sic777.utils.proguard.NoProguard;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.Map;

/**
 * <p>服务提供者协议配置</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-04-24
 */
@Configuration
@ConfigurationProperties(prefix = DubboConstant.PROTOCOL_CONFIG_PREFIX)
@Order(1)
@NoProguard
public class SpringBootDubboProtocolConfig extends SuperDubboProtocolConfig {

    @Bean
    public Map<String, ProtocolConfig> protocolConfigMap() {
        return DubboProtocolConfigManager.instance().build(this);
    }
}
