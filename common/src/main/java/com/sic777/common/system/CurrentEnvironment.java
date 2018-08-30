package com.sic777.common.system;

import com.sic777.common.constants.BaseConstant;
import com.sic777.common.constants.enums.Environment;
import com.sic777.common.laucher.AbstractLauncher;
import com.sic777.utils.system.SystemUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <p>当前环境缓存
 *
 * @author sic777
 * @since 0.0.1
 */
public final class CurrentEnvironment {
    private static CurrentEnvironment singleton = new CurrentEnvironment();

    public static CurrentEnvironment instance() {
        return singleton;
    }

    private CurrentEnvironment() {
        init(false);
    }

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 当前环境,默认为DEVELOP
     */
    private Environment environment = Environment.DEVELOP;
    /**
     * 是否为默认的环境,默认为true
     */
    private boolean isDefault = true;
    /**
     * 是否设置过环境（是否初始化过该类）,默认为false
     */
    private AtomicBoolean isInit = new AtomicBoolean(false);

    /**
     * 初始化环境,仅白名单下的类可以调用
     *
     * @param environment 环境枚举
     */
    public void init(Environment environment) {
        init(environment, true);
    }

    /**
     * 初始化环境,仅白名单下的类可以调用
     */
    public void init() {
        init(true);
    }

    /**
     * 初始化环境
     *
     * @param check 是否需要检查白名单
     */
    private void init(boolean check) {
        String e = System.getProperty(BaseConstant.ENVIRONMENT_FLAG);
        logger.info("-d" + BaseConstant.ENVIRONMENT_FLAG + "=" + e);
        init(Environment.fromEnvironment(e), check);
    }

    /**
     * 初始化环境
     *
     * @param environment
     * @param check       是否需要检查白名单
     */
    private void init(Environment environment, boolean check) {
        if (isInit.compareAndSet(false, true)) {
            if (check) {
                checkWhite();
            }
            this.environment = environment;
            this.isDefault = environment == Environment.DEVELOP;
            logger.info("init current environment:" + environment.getEnvironment());
        }
    }

    /**
     * 校验调用者是否在白名单里
     */
    private void checkWhite() {
        List<String> callerList = Arrays.asList(SystemUtil.getCallers());
        if (!callerList.contains(AbstractLauncher.class.getName())) {
            String topCaller = SystemUtil.getTopCaller();
            logger.error("you have no permission to access this class: " + this.getClass().getName() + ",your class:" + topCaller);
            System.exit(-1);
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
     * 是否为默认(DEVELOP)环境
     *
     * @return
     */
    public boolean isDefault() {
        return isDefault;
    }
}

