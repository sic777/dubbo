package com.sic777.dubbo.common.config.build;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.sic777.common.system.CurrentEnvironment;
import com.sic777.dubbo.common.config.SuperDubboApplicationConfig;
import com.sic777.dubbo.common.constants.DubboConstant;
import com.sic777.dubbo.common.constants.DubboDefaultValue;
import com.sic777.dubbo.common.enums.ApplicationCompilerType;
import com.sic777.dubbo.common.enums.ApplicationLogType;
import com.sic777.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-06-05
 */
public class DubboApplicationConfigManager {
    private static DubboApplicationConfigManager singleton = new DubboApplicationConfigManager();

    public static DubboApplicationConfigManager instance() {
        return singleton;
    }

    private DubboApplicationConfigManager() {
    }

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    private AtomicBoolean isBuild = new AtomicBoolean(false);
    private ApplicationConfig config;


    /**
     * 构造Dubbo应用信息配置
     *
     * @param dubboApplicationConfig
     * @return
     */
    public final ApplicationConfig build(SuperDubboApplicationConfig dubboApplicationConfig) {
        if (isBuild.compareAndSet(false, true)) {
            ApplicationConfig applicationConfig = new ApplicationConfig();
            String name = dubboApplicationConfig.getName();
            String owner = dubboApplicationConfig.getOwner();
            String organization = dubboApplicationConfig.getOrganization();
            String architecture = dubboApplicationConfig.getArchitecture();
            String compiler = dubboApplicationConfig.getCompiler();
            String logger = dubboApplicationConfig.getLogger();
            String version = dubboApplicationConfig.getVersion();

            if (StringUtil.isEmpty(name)) {
                LOG.error(String.format("the value of '${%s.name}' must not be empty!", DubboConstant.APPLICATION_CONFIG_PREFIX));
                System.exit(-1);
            }
            applicationConfig.setName(name);

            applicationConfig.setEnvironment(CurrentEnvironment.instance().getEnvironment().getEnvironment());

            if (StringUtil.isNotEmpty(owner)) {
                applicationConfig.setOwner(owner);
            } else {
                applicationConfig.setOwner(DubboDefaultValue.DEFAULT_OWNER);
                LOG.warn(String.format("the value of '${%s.owner}' is empty, fixed to default value: '%s'", DubboConstant.APPLICATION_CONFIG_PREFIX, DubboDefaultValue.DEFAULT_OWNER));
            }

            if (StringUtil.isNotEmpty(organization)) {
                applicationConfig.setOrganization(organization);
            } else {
                applicationConfig.setOrganization(DubboDefaultValue.DEFAULT_ORGANIZATION);
                LOG.warn(String.format("the value of '${%s.organization}' is empty, fixed to default value: '%s'", DubboConstant.APPLICATION_CONFIG_PREFIX, DubboDefaultValue.DEFAULT_ORGANIZATION));
            }

            if (StringUtil.isNotEmpty(architecture)) {
                applicationConfig.setArchitecture(architecture);
            } else {
                applicationConfig.setArchitecture(DubboDefaultValue.DEFAULT_APPLICATION_ARCHITECTURE);
                LOG.warn(String.format("the value of '${%s.architecture}' is empty, fixed to default value: '%s'", DubboConstant.APPLICATION_CONFIG_PREFIX, DubboDefaultValue.DEFAULT_APPLICATION_ARCHITECTURE));
            }

            if (StringUtil.isEmpty(compiler)) {
                LOG.warn(String.format("the value of '${%s.compiler}' is empty, fixed to default value: '%s'", DubboConstant.APPLICATION_CONFIG_PREFIX, DubboDefaultValue.DEFAULT_COMPILER));
                applicationConfig.setCompiler(DubboDefaultValue.DEFAULT_COMPILER);
            } else {
                ApplicationCompilerType compilerType = ApplicationCompilerType.fromType(compiler);
                if (compilerType == ApplicationCompilerType.UNKNOWN) {
                    LOG.warn(String.format("the value of '${%s.compiler}' is unknown, current value : '%s', fixed to default value: '%s'", DubboConstant.APPLICATION_CONFIG_PREFIX, compiler, DubboDefaultValue.DEFAULT_COMPILER));
                    applicationConfig.setCompiler(DubboDefaultValue.DEFAULT_COMPILER);
                } else {
                    applicationConfig.setCompiler(compiler);
                }
            }

            if (StringUtil.isEmpty(logger)) {
                LOG.warn(String.format("the value of '${%s.logger}' is empty, fixed to default value: '%s'", DubboConstant.APPLICATION_CONFIG_PREFIX, DubboDefaultValue.DEFAULT_LOGGER));
                applicationConfig.setLogger(DubboDefaultValue.DEFAULT_LOGGER);
            } else {
                ApplicationLogType logType = ApplicationLogType.fromType(logger);
                if (logType == ApplicationLogType.UNKNOWN) {
                    LOG.warn(String.format("the value of '${%s.logger}' is unknown, current value : '%s', fixed to default value: '%s'", DubboConstant.APPLICATION_CONFIG_PREFIX, logger, DubboDefaultValue.DEFAULT_LOGGER));
                    applicationConfig.setLogger(DubboDefaultValue.DEFAULT_LOGGER);
                } else {
                    applicationConfig.setLogger(logger);
                }
            }
            if (StringUtil.isNotEmpty(version)) {
                applicationConfig.setVersion(version);
            }
            applicationConfig.setDefault(true);
            this.config = applicationConfig;
        }
        return this.config;
    }
}
