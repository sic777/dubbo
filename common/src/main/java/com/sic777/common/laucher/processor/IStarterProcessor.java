package com.sic777.common.laucher.processor;

/**
 * <p>启动类处理接口</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-05-31
 */
public interface IStarterProcessor {
    /**
     * 在容器初始化之前[同步]
     */
    void before();

    /**
     * 在容器初始化成功之后[同步]
     */
    void after();
}
