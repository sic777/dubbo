package com.sic777.common.laucher.processor;

import com.sic777.utils.proguard.NoProguard;

/**
 * <p>启动类处理接口
 *
 * @author sic777
 * @since 0.0.1
 */
@NoProguard
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
