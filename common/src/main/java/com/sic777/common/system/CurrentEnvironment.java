package com.sic777.common.system;

import com.sic777.common.constants.BaseConstant;
import com.sic777.common.constants.enums.Environment;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <p>当前环境缓存</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-05-31
 */
public final class CurrentEnvironment {
    private static CurrentEnvironment singleton = new CurrentEnvironment();

    public static CurrentEnvironment instance() {
        return singleton;
    }

    private CurrentEnvironment() {
    }

    /**
     * 当前环境
     */
    private Environment environment = Environment.DEVELOP;
    /**
     * 是否为默认的环境
     */
    private boolean isDefault = true;
    /**
     * 是否设置过环境（是否初始化过该类）
     */
    private AtomicBoolean isInit = new AtomicBoolean(false);
    /**
     * 该类调用的白名单
     */
    private final String[] white = {};

    /**
     * 初始化环境
     *
     * @param environment
     */
    public void init(Environment environment) {
        if (isInit.compareAndSet(false, true)) {
            this.environment = environment;
            this.isDefault = environment == Environment.DEVELOP;
        }
    }

    /**
     * 初始化环境
     */
    public void init() {
        if (isInit.compareAndSet(false, true)) {
            this.environment = Environment.fromEnvironment(System.getProperty(BaseConstant.ENVIRONMENT_FLAG));
            this.isDefault = environment == Environment.DEVELOP;
        }
    }


    /**
     * 是否设置过环境（是否初始化过该类）
     *
     * @return
     */
    public boolean isInit() {
        return isInit.get();
    }

    /**
     * 获取当前环境
     *
     * @return
     */
    public Environment getEnvironment() {
        return environment;
    }

    /**
     * 是否为开发环境
     *
     * @return
     */
    public boolean isDefault() {
        return isDefault;
    }
}
