package com.sic777.dubbo.consumer.config;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-05-31
 */
public abstract class SuperDubboConsumerConfig {
    /**
     * 向指定注册中心注册，在多个注册中心时使用，值为<dubbo:registry>的id属性，多个注册中心ID用逗号分隔，如果不想将该服务注册到任何registry，可将值设为N/A
     * 缺省向所有registry注册
     */
    private String registry;

    public String getRegistry() {
        return registry;
    }

    public void setRegistry(String registry) {
        this.registry = registry;
    }
}
