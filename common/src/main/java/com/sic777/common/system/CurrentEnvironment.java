package com.sic777.common.system;

import com.sic777.common.constants.CommonConstant;
import com.sic777.common.enums.Environment;
import com.sic777.common.utils.classes.ClassUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

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
        init();
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
     * 初始化环境
     */
    private void init() {
        String e = System.getProperty(CommonConstant.ENVIRONMENT_FLAG);
        logger.info("-d" + CommonConstant.ENVIRONMENT_FLAG + "=" + e);
        this.environment = Environment.fromEnvironment(e);
        this.isDefault = environment == Environment.DEVELOP;
        logger.info("init current environment:" + environment.getEnvironment());
    }

    /**
     * 校验调用者是否在白名单里
     *
     * @param clz
     * @deprecated
     */
    private void checkWhite(Class<?> clz) {
        List<String> callerList = Arrays.asList(ClassUtil.getCallers());
        if (!callerList.contains(clz.getName())) {
            String topCaller = ClassUtil.getTopCaller();
            logger.error("you have no permission to access this class: " + this.getClass().getName() + ",your class:" + topCaller);
            System.exit(-1);
        }
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

