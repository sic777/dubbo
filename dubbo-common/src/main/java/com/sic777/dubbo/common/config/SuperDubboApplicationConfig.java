package com.sic777.dubbo.common.config;

import com.sic777.common.utils.proguard.NoProguard;

/**
 * <p>应用信息配置</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-04-24
 */
@NoProguard
public abstract class SuperDubboApplicationConfig {
    /**
     * <p>
     * 当前应用名称，用于注册中心计算应用间依赖关系，
     * 注意：消费者和提供者应用名不要一样，此参数不是匹配条件，你当前项目叫什么名字就填什么，和提供者消费者角色无关，
     * 比如：kylin应用调用了morgan应用的服务，则kylin项目配成kylin，morgan项目配成morgan，
     * 可能kylin也提供其它服务给别人使用，但kylin项目永远配成kylin，这样注册中心将显示kylin依赖于morgan
     * </p>
     */
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getArchitecture() {
        return architecture;
    }

    public void setArchitecture(String architecture) {
        this.architecture = architecture;
    }

    public String getCompiler() {
        return compiler;
    }

    public void setCompiler(String compiler) {
        this.compiler = compiler;
    }

    public String getLogger() {
        return logger;
    }

    public void setLogger(String logger) {
        this.logger = logger;
    }
}
