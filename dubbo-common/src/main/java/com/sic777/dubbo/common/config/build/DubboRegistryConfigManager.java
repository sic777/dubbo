package com.sic777.dubbo.common.config.build;

import com.sic777.dubbo.common.config.SuperDubboRegistryConfig;
import com.sic777.dubbo.common.constants.DubboConstant;
import com.sic777.dubbo.common.constants.DubboDefaultValue;
import com.sic777.dubbo.common.enums.RegistryClientType;
import com.sic777.dubbo.common.enums.RegistryProtocolType;
import com.sic777.dubbo.common.enums.RegistryTransportType;
import com.sic777.common.utils.lang.StringUtil;
import org.apache.dubbo.config.RegistryConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-06-05
 */
public class DubboRegistryConfigManager {
    private static DubboRegistryConfigManager singleton = new DubboRegistryConfigManager();

    public static DubboRegistryConfigManager instance() {
        return singleton;
    }

    private DubboRegistryConfigManager() {
    }


    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    private AtomicBoolean isBuild = new AtomicBoolean(false);
    private Map<String, RegistryConfig> configMap;

    /**
     * 构造注册中心配置
     *
     * @param dubboRegistryConfig
     * @return
     */
    @SuppressWarnings("deprecation")
	public final Map<String, RegistryConfig> build(SuperDubboRegistryConfig dubboRegistryConfig) {
        if (isBuild.compareAndSet(false, true)) {
            Map<String, RegistryConfig> registryConfigs = new HashMap<>();
            List<String> address = dubboRegistryConfig.getAddress();
            List<String> id = dubboRegistryConfig.getId();
            List<String> protocol = dubboRegistryConfig.getProtocol();
            List<String> transport = dubboRegistryConfig.getTransport();
            List<String> client = dubboRegistryConfig.getClient();
            List<Integer> port = dubboRegistryConfig.getPort();
            List<String> username = dubboRegistryConfig.getUsername();
            List<String> password = dubboRegistryConfig.getPassword();
            List<Integer> timeout = dubboRegistryConfig.getTimeout();
            List<Integer> session = dubboRegistryConfig.getSession();
            List<String> file = dubboRegistryConfig.getFile();
            List<Integer> wait = dubboRegistryConfig.getWait();
            List<Boolean> check = dubboRegistryConfig.getCheck();
            List<Boolean> register = dubboRegistryConfig.getRegister();
            List<Boolean> subscribe = dubboRegistryConfig.getSubscribe();
            List<Boolean> dynamic = dubboRegistryConfig.getDynamic();

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
                registryConfigs.put(idValue, registry);
            }
            this.configMap = registryConfigs;
        }
        return this.configMap;
    }
}
