package com.sic777.dubbo.provider.config;

import com.sic777.dubbo.common.constants.DubboConstant;
import com.sic777.dubbo.common.constants.DubboDefaultValue;
import com.sic777.dubbo.common.type.ApplicationCompilerType;
import com.sic777.dubbo.common.type.ApplicationLogType;
import com.sic777.dubbo.common.type.Environment;
import com.sic777.utils.string.StringUtil;
import com.alibaba.dubbo.config.ApplicationConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>应用信息配置</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-04-24
 */
@Configuration
@ConfigurationProperties(prefix = DubboConstant.APPLICATION_CONFIG_PREFIX)
public class DubboApplicationConfig {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    /**
     * <p>
     * 当前应用名称，用于注册中心计算应用间依赖关系，
     * 注意：消费者和提供者应用名不要一样，此参数不是匹配条件，你当前项目叫什么名字就填什么，和提供者消费者角色无关，
     * 比如：kylin应用调用了morgan应用的服务，则kylin项目配成kylin，morgan项目配成morgan，
     * 可能kylin也提供其它服务给别人使用，但kylin项目永远配成kylin，这样注册中心将显示kylin依赖于morgan
     * </p>
     */
//    @Value("${" + DubboConstant.APPLICATION_CONFIG_PREFIX + ".name}")
    private String name;
    /**
     * 当前应用的版本
     */
    private String version;
    /**
     * 应用负责人，用于服务治理，请填写负责人公司邮箱前缀
     */
    private String owner;
    /**
     * 组织名称(BU或部门)，用于注册中心区分服务来源，此配置项建议不要使用autoconfig，直接写死在配置中，比如china,intl,itu,crm,asc,dw,aliexpress等
     */
    private String organization;
    /**
     * 用于服务分层对应的架构。如，intl、china。不同的架构使用不同的分层
     */
    private String architecture;
    /**
     * 应用环境，如：develop/test/product，不同环境使用不同的缺省值，以及作为只用于开发测试功能的限制条件
     * 默认：develop
     */
    private String environment;
    /**
     * Java字节码编译器，用于动态类的生成，
     * 可选：jdk或javassist
     * 默认：javassist
     */
    private String compiler;
    /**
     * 日志输出方式，
     * 可选：slf4j,jcl,log4j,jdk
     * 官方默认：slf4j
     * 程序默认：log4j
     */
    private String logger;
    /**
     * 应用配置对象
     */
    private ApplicationConfig config;

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String getOwner() {
        return owner;
    }

    public String getOrganization() {
        return organization;
    }

    public String getArchitecture() {
        return architecture;
    }

    public String getEnvironment() {
        return environment;
    }

    public String getCompiler() {
        return compiler;
    }

    public String getLogger() {
        return logger;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public void setArchitecture(String architecture) {
        this.architecture = architecture;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public void setCompiler(String compiler) {
        this.compiler = compiler;
    }

    public void setLogger(String logger) {
        this.logger = logger;
    }

    public ApplicationConfig getConfig() {
        return config;
    }

    /**
     * 构造Dubbo应用信息配置
     *
     * @return
     */
    @Bean
    public ApplicationConfig defaultDubboApplicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
//         必填项不使用@Value注解,简化报错输出
        if (StringUtil.isEmpty(name)) {
            LOG.error(String.format("the value of '${%s.name}' must not be empty!", DubboConstant.APPLICATION_CONFIG_PREFIX));
            System.exit(-1);
        }
        applicationConfig.setName(name);

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

        if (StringUtil.isEmpty(environment)) {
            LOG.warn(String.format("the value of '${%s.environment}' is empty, fixed to default value: '%s'", DubboConstant.APPLICATION_CONFIG_PREFIX, DubboDefaultValue.DEFAULT_ENV));
            applicationConfig.setEnvironment(DubboDefaultValue.DEFAULT_ENV);
            this.environment = DubboDefaultValue.DEFAULT_ENV;
        } else {
            Environment env = Environment.fromEnvironment(environment);
            if (env == Environment.UNKNOWN) {
                LOG.warn(String.format("the value of '${%s.environment}' is unknown, current value : '%s', fixed to default value: '%s'", DubboConstant.APPLICATION_CONFIG_PREFIX, environment, DubboDefaultValue.DEFAULT_ENV));
                applicationConfig.setEnvironment(DubboDefaultValue.DEFAULT_ENV);
                this.environment = DubboDefaultValue.DEFAULT_ENV;
            } else {
                applicationConfig.setEnvironment(env.getEnvironment());
                this.environment = env.getEnvironment();//更新当前值
            }
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
        return applicationConfig;
    }

}
