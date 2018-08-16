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

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

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
     * 初始化环境,仅白名单下的类可以调用
     *
     * @param environment
     */
    public void init(Environment environment) {
        checkWhite();
        if (isInit.compareAndSet(false, true)) {
            this.environment = environment;
            this.isDefault = environment == Environment.DEVELOP;
        }
    }

    /**
     * 初始化环境,仅白名单下的类可以调用
     */
    public void init() {
        checkWhite();
        if (isInit.compareAndSet(false, true)) {
            this.environment = Environment.fromEnvironment(System.getProperty(BaseConstant.ENVIRONMENT_FLAG));
            this.isDefault = environment == Environment.DEVELOP;
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
     * 是否为默认(开发)环境
     *
     * @return
     */
    public boolean isDefault() {
        return isDefault;
    }
}
