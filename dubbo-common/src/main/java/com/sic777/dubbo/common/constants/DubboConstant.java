package com.sic777.dubbo.common.constants;

import com.sic777.common.utils.proguard.NoProguard;

/**
 * <p>Dubbo常量</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-04-26
 */
@NoProguard
public class DubboConstant {
    /**
     * dubbo应用配置 前缀
     */
    public static final String APPLICATION_CONFIG_PREFIX = "dubbo.application";
    /**
     * dubbo协议配置 前缀
     */
    public static final String PROTOCOL_CONFIG_PREFIX = "dubbo.protocol";
    /**
     * dubbo服务提供者配置 前缀
     */
    public static final String PROVIDER_CONFIG_PREFIX = "dubbo.provider";
    /**
     * dubbo注册中心配置 前缀
     */
    public static final String REGISTRY_CONFIG_PREFIX = "dubbo.registry";
    /**
     * dubbo服务消费者配置 前缀
     */
    public static final String CONSUMER_CONFIG_PREFIX = "dubbo.consumer";
}
