package com.sic777.dubbo.comsumer.common.config.build;

import com.sic777.dubbo.common.config.SuperDubboApplicationConfig;
import com.sic777.dubbo.common.config.SuperDubboRegistryConfig;
import com.sic777.dubbo.common.config.build.DubboApplicationConfigManager;
import com.sic777.dubbo.common.config.build.DubboRegistryConfigManager;
import com.sic777.dubbo.common.constants.DubboConstant;
import com.sic777.dubbo.common.constants.DubboDefaultValue;
import com.sic777.dubbo.comsumer.common.config.SuperDubboConsumerConfig;
import com.sic777.common.utils.lang.StringUtil;
import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.sic777.common.utils.proguard.NoProguard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-06-05
 */
@NoProguard
public class DubboConsumerConfigManager {
    private static DubboConsumerConfigManager singleton = new DubboConsumerConfigManager();

    public static DubboConsumerConfigManager instance() {
        return singleton;
    }

    private DubboConsumerConfigManager() {
    }

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    private AtomicBoolean isBuild = new AtomicBoolean(false);
    private ConsumerConfig config;

    /**
     * 构造服务消费者默认配置
     *
     * @param dubboApplicationConfig
     * @param dubboRegistryConfig
     * @param dubboConsumerConfig
     * @return
     */
    public ConsumerConfig build(SuperDubboApplicationConfig dubboApplicationConfig, SuperDubboRegistryConfig dubboRegistryConfig, SuperDubboConsumerConfig dubboConsumerConfig) {
        if (isBuild.compareAndSet(false, true)) {
            ConsumerConfig consumerConfig = new ConsumerConfig();
            String registry = dubboConsumerConfig.getRegistry();
            consumerConfig.setFilter(DubboDefaultValue.DEFAULT_CONSUMER_FILTERS);
            ApplicationConfig applicationConfig = DubboApplicationConfigManager.instance().build(dubboApplicationConfig);
            consumerConfig.setApplication(applicationConfig);
            Map<String, RegistryConfig> registryConfig = DubboRegistryConfigManager.instance().build(dubboRegistryConfig);
            if (StringUtil.isNotEmpty(registry)) {//TODO check,这部分实现需要测试
                String[] rs = registry.split(",");
                if (rs.length == 1) {
                    RegistryConfig cf = registryConfig.get(registry);
                    if (null == cf) {
                        LOG.error(String.format("this registry is not exists, '${%s.registry}' : '%s'", DubboConstant.PROVIDER_CONFIG_PREFIX, registry));
                        System.exit(-1);
                    }
                    consumerConfig.setRegistry(cf);
                } else {//向用户配置的注册中心注册服务
                    List<RegistryConfig> ls = new ArrayList<>();
                    for (String r : rs) {
                        RegistryConfig cf = registryConfig.get(r);
                        if (null == cf) {
                            LOG.error(String.format("this registry is not exists, '${%s.registry}' : '%s'", DubboConstant.PROVIDER_CONFIG_PREFIX, r));
                            System.exit(-1);
                        }
                        ls.add(cf);
                    }
                    consumerConfig.setRegistries(ls);
                }
            } else {//向所有注册中心注册服务
                List<RegistryConfig> ls = new ArrayList<>();
                ls.addAll(registryConfig.values());
                consumerConfig.setRegistries(ls);
            }
            this.config = consumerConfig;
        }
        return this.config;
    }
}
