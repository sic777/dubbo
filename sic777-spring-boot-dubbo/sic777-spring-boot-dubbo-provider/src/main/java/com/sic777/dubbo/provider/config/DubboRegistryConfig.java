package com.sic777.dubbo.provider.config;

import com.sic777.dubbo.common.constants.DubboConstant;
import com.sic777.dubbo.common.constants.DubboDefaultValue;
import com.sic777.dubbo.common.type.RegistryClientType;
import com.sic777.dubbo.common.type.RegistryProtocolType;
import com.sic777.dubbo.common.type.RegistryTransportType;
import com.sic777.dubbo.provider.spring.context.SpringContext;
import com.sic777.dubbo.provider.spring.register.SpringBeanRegistry;
import com.sic777.utils.string.StringUtil;
import com.alibaba.dubbo.config.RegistryConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>注册中心配置</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-04-24
 */
@Configuration
@ConfigurationProperties(prefix = DubboConstant.REGISTRY_CONFIG_PREFIX)
public class DubboRegistryConfig {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SpringBeanRegistry springBeanRegistry;

    /**
     * 注册中心引用BeanId，可以在<dubbo:service registry="">或<dubbo:reference registry="">中引用此ID
     */
    private List<String> id;
    /**
     * [必填]
     * 注册中心服务器地址
     */
    private List<String> address;
    /**
     * 注同中心地址协议，支持zookeeper、multicast、redis
     * 默认dubbo
     */
    private List<String> protocol;
    /**
     * 注册中心缺省端口，当address没有带端口时使用此端口做为缺省值
     * 默认9090
     */
    private List<Integer> port;
    /**
     * 登录注册中心用户名，如果注册中心不需要验证可不填
     */
    private List<String> username;
    /**
     * 登录注册中心密码，如果注册中心不需要验证可不填
     */
    private List<String> password;
    /**
     * 网络传输方式，可选mina,netty
     * 默认netty
     */
    private List<String> transport;
    /**
     * 注册中心请求超时时间(毫秒)
     * 默认5000
     */
    private List<Integer> timeout;
    /**
     * 注册中心会话超时时间(毫秒)，用于检测提供者非正常断线后的脏数据，比如用心跳检测的实现，此时间就是心跳间隔，不同注册中心实现不一样。
     * 默认60000
     */
    private List<Integer> session;
    /**
     * 使用文件缓存注册中心地址列表及服务提供者列表，应用重启时将基于此文件恢复，注意：两个注册中心不能使用同一文件存储
     */
    private List<String> file;
    /**
     * 停止时等待通知完成时间(毫秒)
     * 默认 0
     */
    private List<Integer> wait;
    /**
     * 注册中心不存在时，是否报错
     * 默认true
     */
    private List<Boolean> check;
    /**
     * 是否向此注册中心注册服务，如果设为false，将只订阅，不注册
     * 默认true
     */
    private List<Boolean> register;
    /**
     * 是否向此注册中心订阅服务，如果设为false，将只注册，不订阅
     * true
     */
    private List<Boolean> subscribe;
    /**
     * 服务是否动态注册，如果设为false，注册后将显示后disable状态，需人工启用，并且服务提供者停止时，也不会自动取消册，需人工禁用。
     * 默认 true
     */
    private List<Boolean> dynamic;
    /**
     * 客户端地址
     * 默认：curator
     */
    private List<String> client;
    /**
     * 配置对象列表
     */
    private Collection<RegistryConfig> configs;

    public List<String> getId() {
        return id;
    }

    public void setId(List<String> id) {
        this.id = id;
    }

    public List<String> getAddress() {
        return address;
    }

    public void setAddress(List<String> address) {
        this.address = address;
    }

    public List<String> getProtocol() {
        return protocol;
    }

    public void setProtocol(List<String> protocol) {
        this.protocol = protocol;
    }

    public List<Integer> getPort() {
        return port;
    }

    public void setPort(List<Integer> port) {
        this.port = port;
    }

    public List<String> getUsername() {
        return username;
    }

    public void setUsername(List<String> username) {
        this.username = username;
    }

    public List<String> getPassword() {
        return password;
    }

    public void setPassword(List<String> password) {
        this.password = password;
    }

    public List<String> getTransport() {
        return transport;
    }

    public void setTransport(List<String> transport) {
        this.transport = transport;
    }

    public List<Integer> getTimeout() {
        return timeout;
    }

    public void setTimeout(List<Integer> timeout) {
        this.timeout = timeout;
    }

    public List<Integer> getSession() {
        return session;
    }

    public void setSession(List<Integer> session) {
        this.session = session;
    }

    public List<String> getFile() {
        return file;
    }

    public void setFile(List<String> file) {
        this.file = file;
    }

    public List<Integer> getWait() {
        return wait;
    }

    public void setWait(List<Integer> wait) {
        this.wait = wait;
    }

    public List<Boolean> getCheck() {
        return check;
    }

    public void setCheck(List<Boolean> check) {
        this.check = check;
    }

    public List<Boolean> getRegister() {
        return register;
    }

    public void setRegister(List<Boolean> register) {
        this.register = register;
    }

    public List<Boolean> getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(List<Boolean> subscribe) {
        this.subscribe = subscribe;
    }

    public List<Boolean> getDynamic() {
        return dynamic;
    }

    public void setDynamic(List<Boolean> dynamic) {
        this.dynamic = dynamic;
    }

    public List<String> getClient() {
        return client;
    }

    public void setClient(List<String> client) {
        this.client = client;
    }

    public Collection<RegistryConfig> getConfigs() {
        return configs;
    }

    /**
     * 构造Dubbo注册中心配置
     * 不使用@Bean注入,由程序手动注入(为了支持多注册中心)
     *
     * @return
     */
    @SuppressWarnings("deprecation")
	public Map<String, RegistryConfig> buildRegistryConfig() {
        Map<String, RegistryConfig> registryConfigs = new HashMap<>();
//        必填项不使用@Value注解,简化报错输出
        if (null == address || address.isEmpty()) {
            LOG.error(String.format("the value of '${%s.address}' must not be empty!", DubboConstant.REGISTRY_CONFIG_PREFIX));
            System.exit(-1);
        }
        int size = address.size();
        if (size > 1 && (id == null || id.isEmpty())) {//如果存在多注册中心,则id必须不能为空
            LOG.error(String.format("the value of '${%s.id}' must not be empty!", DubboConstant.REGISTRY_CONFIG_PREFIX));
            System.exit(-1);
        }
        if (id != null && id.size() < size) {
            LOG.error(String.format("the length of '${%s.id}' must be greater than or equal to the '${%s.address}'", DubboConstant.REGISTRY_CONFIG_PREFIX, DubboConstant.REGISTRY_CONFIG_PREFIX));
            System.exit(-1);
        }

        for (int i = 0; i < size; i++) {
            RegistryConfig registry = new RegistryConfig();
            registry.setAddress(address.get(i));

            String pro;
            if (null == protocol || protocol.isEmpty()) {
                LOG.warn(String.format("the value of '${%s.protocol}' is empty, fixed to default value: '%s'", DubboConstant.REGISTRY_CONFIG_PREFIX, DubboDefaultValue.DEFAULT_REGISTER_PROTOCOL));
                registry.setProtocol(DubboDefaultValue.DEFAULT_REGISTER_PROTOCOL);
                pro = DubboDefaultValue.DEFAULT_REGISTER_PROTOCOL;
            } else {
                pro = protocol.get(i);
                RegistryProtocolType protocolType = RegistryProtocolType.fromType(pro);
                if (RegistryProtocolType.UNKNOWN == protocolType) {
                    LOG.warn(String.format("the value of '${%s.protocol}' is unknown, current value : '%s', fixed to default value: '%s'", DubboConstant.REGISTRY_CONFIG_PREFIX, pro, DubboDefaultValue.DEFAULT_REGISTER_PROTOCOL));
                    registry.setProtocol(DubboDefaultValue.DEFAULT_REGISTER_PROTOCOL);
                    pro = DubboDefaultValue.DEFAULT_REGISTER_PROTOCOL;
                } else {
                    registry.setProtocol(pro);
                }
            }

            if (null == transport || transport.isEmpty()) {
                LOG.warn(String.format("the value of '${%s.transport}' is empty, fixed to default value: '%s'", DubboConstant.REGISTRY_CONFIG_PREFIX, DubboDefaultValue.DEFAULT_DUBBO_TRANSPORTER));
                registry.setTransporter(DubboDefaultValue.DEFAULT_DUBBO_TRANSPORTER);
            } else {
                String trans = transport.get(i);
                RegistryTransportType transportType = RegistryTransportType.fromType(trans);
                if (transportType == RegistryTransportType.UNKNOWN) {
                    LOG.warn(String.format("the value of '${%s.transport}' is unknown, current value : '%s', fixed to default value: '%s'", DubboConstant.REGISTRY_CONFIG_PREFIX, trans, DubboDefaultValue.DEFAULT_DUBBO_TRANSPORTER));
                    registry.setTransporter(DubboDefaultValue.DEFAULT_DUBBO_TRANSPORTER);
                } else {
                    registry.setTransporter(transportType.getType());
                }
            }

            if (null == client || client.isEmpty()) {
                LOG.warn(String.format("the value of '${%s.client}' is empty, fixed to default value: '%s'", DubboConstant.REGISTRY_CONFIG_PREFIX, DubboDefaultValue.DEFAULT_ZOOKEEPER_CLIENT));
                registry.setClient(DubboDefaultValue.DEFAULT_ZOOKEEPER_CLIENT);
            } else {
                String cli = client.get(i);
                RegistryClientType clientType = RegistryClientType.fromType(cli);
                if (clientType == RegistryClientType.UNKNOWN) {
                    LOG.warn(String.format("the value of '${%s.client}' is unknown, current value : '%s', fixed to default value: '%s'", DubboConstant.REGISTRY_CONFIG_PREFIX, cli, DubboDefaultValue.DEFAULT_ZOOKEEPER_CLIENT));
                    registry.setClient(DubboDefaultValue.DEFAULT_ZOOKEEPER_CLIENT);
                } else {
                    registry.setClient(cli);
                }
            }
            String idValue;
            if ((id == null || id.isEmpty()) && size == 1) {
                String DEFAULT_ID = String.format(DubboDefaultValue.DEFAULT_REGISTRY_ID, pro);
                LOG.warn(String.format("the value of '${%s.id}' is empty, fixed to default value: '%s'", DubboConstant.REGISTRY_CONFIG_PREFIX, DEFAULT_ID));
                registry.setId(DEFAULT_ID);
                idValue = DEFAULT_ID;
            } else {
                registry.setId(id.get(i));
                idValue = id.get(i);
            }

            if (null != port && null != port.get(i)) {
                registry.setPort(port.get(i));
            }

            if (null != username && StringUtil.isNotEmpty(username.get(i))) {
                registry.setUsername(username.get(i));
            }

            if (null != password && StringUtil.isNotEmpty(password.get(i))) {
                registry.setPassword(password.get(i));
            }

            if (null != timeout && null != timeout.get(i)) {
                registry.setTimeout(timeout.get(i));
            } else {
                LOG.warn(String.format("the value of '${%s.timeout}' is empty, fixed to default value: '%s'", DubboConstant.REGISTRY_CONFIG_PREFIX, DubboDefaultValue.DEFAULT_REGISTER_TIMEOUT));
                registry.setTimeout(DubboDefaultValue.DEFAULT_REGISTER_TIMEOUT);
            }

            if (null != session && null != session.get(i)) {
                registry.setSession(session.get(i));
            } else {
                LOG.warn(String.format("the value of '${%s.session}' is empty, fixed to default value: '%s'", DubboConstant.REGISTRY_CONFIG_PREFIX, DubboDefaultValue.DEFAULT_REGISTER_SESSION_TIMEOUT));
                registry.setSession(DubboDefaultValue.DEFAULT_REGISTER_SESSION_TIMEOUT);
            }

            if (null != file && StringUtil.isNotEmpty(file.get(i))) {
                registry.setFile(file.get(i));
            }

            if (null != wait && null != wait.get(i)) {
                registry.setWait(wait.get(i));
            }

            if (null != check && null != check.get(i)) {
                registry.setCheck(check.get(i));
            } else {
                registry.setCheck(true);
            }

            if (null != register && null != register.get(i)) {
                registry.setRegister(register.get(i));
            } else {
                registry.setRegister(true);
            }

            if (null != subscribe && null != subscribe.get(i)) {
                registry.setSubscribe(subscribe.get(i));
            } else {
                registry.setSubscribe(true);
            }

            if (null != dynamic && null != dynamic.get(i)) {
                registry.setDynamic(dynamic.get(i));
            } else {
                registry.setDynamic(true);
            }

            if (i == 0) {//设置第一个为默认的注册中心
                registry.setDefault(true);
                LOG.info(String.format("set '%s' as the default registry", idValue));
            }

            registryConfigs.putIfAbsent(idValue, registry);
            if (!SpringContext.BEAN_FACTORY.containsBean(idValue)) {
                springBeanRegistry.register(idValue, registry);
            }
        }
        this.configs = registryConfigs.values();
        return registryConfigs;
    }
}
