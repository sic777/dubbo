package com.sic777.dubbo.provider.config.build;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.sic777.common.system.CurrentEnvironment;
import com.sic777.dubbo.common.config.SuperDubboApplicationConfig;
import com.sic777.dubbo.common.config.SuperDubboRegistryConfig;
import com.sic777.dubbo.common.config.build.DubboApplicationConfigManager;
import com.sic777.dubbo.common.config.build.DubboRegistryConfigManager;
import com.sic777.dubbo.common.constants.DubboConstant;
import com.sic777.dubbo.common.constants.DubboDefaultValue;
import com.sic777.dubbo.common.enums.DubboProtocolType;
import com.sic777.dubbo.common.enums.LoadBalanceType;
import com.sic777.dubbo.common.enums.ProtocolThreadPoolType;
import com.sic777.dubbo.provider.config.SuperDubboProtocolConfig;
import com.sic777.dubbo.provider.config.SuperDubboProviderConfig;
import com.sic777.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
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
public class DubboProviderConfigManager {
    private static DubboProviderConfigManager singleton = new DubboProviderConfigManager();

    public static DubboProviderConfigManager instance() {
        return singleton;
    }

    private DubboProviderConfigManager() {
    }

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    private AtomicBoolean isBuild = new AtomicBoolean(false);
    private ProviderConfig config;

    /**
     * 构造服务提供者缺省配置
     *
     * @param dubboApplicationConfig
     * @param dubboRegistryConfig
     * @param dubboProtocolConfig
     * @param dubboProviderConfig
     * @return
     */
    @SuppressWarnings("deprecation")
    public final ProviderConfig build(SuperDubboApplicationConfig dubboApplicationConfig, SuperDubboRegistryConfig dubboRegistryConfig, SuperDubboProtocolConfig dubboProtocolConfig, SuperDubboProviderConfig dubboProviderConfig) {
        if (isBuild.compareAndSet(false, true)) {
            ProviderConfig providerConfig = new ProviderConfig();
            String id = dubboProviderConfig.getId();
            String protocol = dubboProviderConfig.getProtocol();
            String host = dubboProviderConfig.getHost();
            Integer threads = dubboProviderConfig.getThreads();
            Integer payload = dubboProviderConfig.getPayload();
            String path = dubboProviderConfig.getPath();
            String server = dubboProviderConfig.getServer();
            String client = dubboProviderConfig.getClient();
            String codec = dubboProviderConfig.getCodec();
            String serialization = dubboProviderConfig.getSerialization();
            String filter = dubboProviderConfig.getFilter();
            String listener = dubboProviderConfig.getListener();
            String threadpool = dubboProviderConfig.getThreadpool();
            Integer accepts = dubboProviderConfig.getAccepts();
            String version = dubboProviderConfig.getVersion();
            String group = dubboProviderConfig.getGroup();
            Integer delay = dubboProviderConfig.getDelay();
            Integer timeout = dubboProviderConfig.getTimeout();
            Integer retries = dubboProviderConfig.getRetries();
            Integer connections = dubboProviderConfig.getConnections();
            String loadbalance = dubboProviderConfig.getLoadbalance();
            Boolean async = dubboProviderConfig.getAsync();
            Boolean stub = dubboProviderConfig.getStub();
            Boolean mock = dubboProviderConfig.getMock();
            Boolean token = dubboProviderConfig.getToken();
            Boolean dynamic = dubboProviderConfig.getDynamic();
            String accesslog = dubboProviderConfig.getAccesslog();
            String owner = dubboProviderConfig.getOwner();
            String document = dubboProviderConfig.getDocument();
            Integer weight = dubboProviderConfig.getWeight();
            Integer executes = dubboProviderConfig.getExecutes();
            Integer actives = dubboProviderConfig.getActives();
            String proxy = dubboProviderConfig.getProxy();
            String cluster = dubboProviderConfig.getCluster();
            Boolean deprecated = dubboProviderConfig.getDeprecated();
            Integer queues = dubboProviderConfig.getQueues();
            String charset = dubboProviderConfig.getCharset();
            Integer buffer = dubboProviderConfig.getBuffer();
            Integer iothreads = dubboProviderConfig.getIothreads();
            String telnet = dubboProviderConfig.getTelnet();
            String registry = dubboProviderConfig.getRegistry();
            String layer = dubboProviderConfig.getLayer();

            Map<String, ProtocolConfig> protocolConfig = DubboProtocolConfigManager.instance().build(dubboProtocolConfig);
            DubboProtocolType protocolType = DubboProtocolType.fromType(protocol);
            if (DubboProtocolType.UNKNOWN == protocolType) {//默认查找dubbo协议
                LOG.warn(String.format("the value of '${%s.protocol}' is unknown, fixed to default value: '%s'", DubboConstant.PROVIDER_CONFIG_PREFIX, DubboDefaultValue.DEFAULT_DUBBO_PROTOCOL));
                ProtocolConfig cf = protocolConfig.get(String.format(DubboDefaultValue.DEFAULT_DUBBO_PROTOCOL_ID, DubboDefaultValue.DEFAULT_DUBBO_PROTOCOL));
                if (null == cf) {
                    LOG.error(String.format("the default protocol is not exists, '${%s.protocol}' : '%s'", DubboConstant.PROVIDER_CONFIG_PREFIX, DubboDefaultValue.DEFAULT_DUBBO_PROTOCOL));
                    System.exit(-1);
                }
                providerConfig.setProtocol(cf);
                protocolType = DubboDefaultValue.DEFAULT_DUBBO_PROTOCOL_ENUM;
            } else {
                ProtocolConfig cf = protocolConfig.get(protocol);
                if (null == cf) { //TODO check 这部分实现需要检验代码
                    LOG.error(String.format("the current protocol is not exists, '${%s.protocol}' : '%s'", DubboConstant.PROVIDER_CONFIG_PREFIX, protocol));
                    System.exit(-1);
                }
                providerConfig.setProtocol(cf);
            }

            if (StringUtil.isNotEmpty(host)) {
                providerConfig.setHost(host);
            }

            if (null != threads) {
                providerConfig.setThreads(threads);
            } else {
                providerConfig.setThreads(DubboDefaultValue.DEFAULT_THREAD_SIZE);
                LOG.warn(String.format("the value of '${%s.threads}' is empty, fixed to default value: '%s'", DubboConstant.PROVIDER_CONFIG_PREFIX, DubboDefaultValue.DEFAULT_THREAD_SIZE));
            }

            if (null != payload) {
                providerConfig.setPayload(payload);
            } else {
                providerConfig.setPayload(DubboDefaultValue.DEFAULT_PAYLOAD);
                LOG.warn(String.format("the value of '${%s.payload}' is empty, fixed to default value: '%s'", DubboConstant.PROVIDER_CONFIG_PREFIX, DubboDefaultValue.DEFAULT_PAYLOAD));
            }

            if (StringUtil.isNotEmpty(path)) {
                providerConfig.setPath(path);
            }

            if (StringUtil.isNotEmpty(server)) {
                providerConfig.setServer(server);
            } else {
                String DEFAULT_TRANSPORTER = protocolType == DubboProtocolType.DUBBO
                        ? DubboDefaultValue.DEFAULT_DUBBO_TRANSPORTER : DubboDefaultValue.DEFAULT_HTTP_TRANSPORTER;
                providerConfig.setTransporter(DEFAULT_TRANSPORTER);
                LOG.warn(String.format("the value of '${%s.server}' is empty, fixed to default value: '%s'", DubboConstant.PROVIDER_CONFIG_PREFIX, DEFAULT_TRANSPORTER));
            }

            if (StringUtil.isNotEmpty(client)) {
                providerConfig.setClient(client);
            } else {
                if (protocolType == DubboProtocolType.DUBBO) {
                    providerConfig.setClient(DubboDefaultValue.DEFAULT_DUBBO_TRANSPORTER);
                    LOG.warn(String.format("the value of '${%s.client}' is empty, fixed to default value: '%s'", DubboConstant.PROVIDER_CONFIG_PREFIX, DubboDefaultValue.DEFAULT_DUBBO_TRANSPORTER));
                }
            }

            if (StringUtil.isNotEmpty(codec)) {
                providerConfig.setCodec(codec);
            }

            if (StringUtil.isNotEmpty(serialization)) {
                providerConfig.setSerialization(serialization);
            }

            if (StringUtil.isNotEmpty(serialization)) {
                providerConfig.setSerialization(serialization);
            } else {
                String DEFAULT_SERIALIZATION = protocolType == DubboProtocolType.DUBBO
                        ? DubboDefaultValue.DEFAULT_DUBBO_SERIALIZATION
                        : (protocolType == DubboProtocolType.RMI
                        ? DubboDefaultValue.DEFAULT_RIM_SERIALIZATION
                        : DubboDefaultValue.DEFAULT_HTTP_SERIALIZATION);
                providerConfig.setSerialization(DEFAULT_SERIALIZATION);
                LOG.warn(String.format("the value of '${%s.serialization}' is empty, fixed to default value: '%s'", DubboConstant.PROVIDER_CONFIG_PREFIX, DEFAULT_SERIALIZATION));
            }

            if (StringUtil.isNotEmpty(filter)) {
                providerConfig.setFilter(filter);
            } else {
                providerConfig.setFilter(DubboDefaultValue.DEFAULT_PROVIDER_FILTERS);
                LOG.warn(String.format("the value of '${%s.filter}' is empty, fixed to default value: '%s'", DubboConstant.PROVIDER_CONFIG_PREFIX, DubboDefaultValue.DEFAULT_PROVIDER_FILTERS));
            }

            if (StringUtil.isNotEmpty(listener)) {
                providerConfig.setListener(listener);
            }

            if (StringUtil.isEmpty(threadpool)) {
                LOG.warn(String.format("the value of '${%s.threadpool}' is empty, fixed to default value: '%s'", DubboConstant.PROVIDER_CONFIG_PREFIX, DubboDefaultValue.DEFAULT_THREAD_POOL));
                providerConfig.setThreadpool(DubboDefaultValue.DEFAULT_THREAD_POOL);
            } else {
                ProtocolThreadPoolType threadPoolType = ProtocolThreadPoolType.fromType(threadpool);
                if (threadPoolType == ProtocolThreadPoolType.UNKNOWN) {
                    LOG.warn(String.format("the value of '${%s.threadpool}' is unknown, current value : '%s', fixed to default value: '%s'", DubboConstant.PROVIDER_CONFIG_PREFIX, threadpool, DubboDefaultValue.DEFAULT_THREAD_POOL));
                    providerConfig.setThreadpool(DubboDefaultValue.DEFAULT_THREAD_POOL);
                } else {
                    providerConfig.setThreadpool(threadpool);
                }
            }

            if (null != accepts) {
                providerConfig.setAccepts(accepts);
            }

            if (StringUtil.isNotEmpty(version)) {
                providerConfig.setVersion(version);
            }

            if (StringUtil.isNotEmpty(group)) {
                providerConfig.setGroup(group);
            }

            if (null != delay) {
                providerConfig.setDelay(delay);
            } else {
                providerConfig.setDelay(DubboDefaultValue.DEFAULT_SERVER_REGISTER_DELAY);
                LOG.warn(String.format("the value of '${%s.delay}' is empty, fixed to default value: '%s'", DubboConstant.PROVIDER_CONFIG_PREFIX, DubboDefaultValue.DEFAULT_SERVER_REGISTER_DELAY));
            }
            if (null != timeout) {
                providerConfig.setTimeout(timeout);
            } else {
                providerConfig.setTimeout(CurrentEnvironment.instance().isDefault() ? DubboDefaultValue.DEFAULT_REMOTE_SERVER_TIMEOUT_DEFAULT_ENV : DubboDefaultValue.DEFAULT_REMOTE_SERVER_TIMEOUT_NON_DEFAULT_ENV);
            }

            if (null != retries) {
                providerConfig.setRetries(retries);
            } else {
                providerConfig.setRetries(DubboDefaultValue.DEFAULT_REMOTE_SERVER_RETRIES);
                LOG.warn(String.format("the value of '${%s.retries}' is empty, fixed to default value: '%s'", DubboConstant.PROVIDER_CONFIG_PREFIX, DubboDefaultValue.DEFAULT_REMOTE_SERVER_RETRIES));
            }

            if (null != connections) {
                providerConfig.setConnections(connections);
            }

            if (StringUtil.isEmpty(loadbalance)) {
                LOG.warn(String.format("the value of '${%s.loadbalance}' is empty, fixed to default value: '%s'", DubboConstant.PROVIDER_CONFIG_PREFIX, DubboDefaultValue.DEFAULT_LOAD_BALANCE));
                providerConfig.setLoadbalance(DubboDefaultValue.DEFAULT_LOAD_BALANCE);
            } else {
                LoadBalanceType loadBalanceType = LoadBalanceType.fromType(loadbalance);
                if (loadBalanceType == LoadBalanceType.UNKNOWN) {
                    LOG.warn(String.format("the value of '${%s.loadbalance}' is unknown, current value : '%s', fixed to default value: '%s'", DubboConstant.PROVIDER_CONFIG_PREFIX, loadbalance, DubboDefaultValue.DEFAULT_LOAD_BALANCE));
                    providerConfig.setLoadbalance(DubboDefaultValue.DEFAULT_LOAD_BALANCE);
                } else {
                    providerConfig.setLoadbalance(loadbalance);
                }
            }

            if (null != async) {
                providerConfig.setAsync(async);
            } else {
                providerConfig.setAsync(false);
            }

            if (null != stub) {
                providerConfig.setStub(stub);
            } else {
                providerConfig.setStub(false);
            }

            if (null != mock) {
                providerConfig.setMock(mock);
            } else {
                providerConfig.setMock(false);
            }

            if (null != token) {
                providerConfig.setToken(token);
            } else {//可以根据环境进行切换
                if (CurrentEnvironment.instance().isDefault()) {
                    providerConfig.setToken(false);//设置为false，在开发环境中，消费者可以绕过注册中心直连服务提供者
                } else {
                    providerConfig.setToken(true);//强制通过注册中心获取服务列表
                }
            }

            if (null != dynamic) {
                providerConfig.setDynamic(dynamic);
            } else {
                providerConfig.setDynamic(true);
            }

            if (StringUtil.isNotEmpty(accesslog)) {
                providerConfig.setAccesslog(accesslog);
            }

            if (StringUtil.isNotEmpty(owner)) {
                providerConfig.setOwner(owner);
            } else {
                providerConfig.setOwner(DubboDefaultValue.DEFAULT_OWNER);
                LOG.warn(String.format("the value of '${%s.owner}' is empty, fixed to default value: '%s'", DubboConstant.PROVIDER_CONFIG_PREFIX, DubboDefaultValue.DEFAULT_OWNER));
            }

            if (StringUtil.isNotEmpty(document)) {
                providerConfig.setDocument(document);
            }

            if (null != weight) {
                providerConfig.setWeight(weight);
            }

            if (null != executes) {
                providerConfig.setExecutes(executes);
            }

            if (null != actives) {
                providerConfig.setActives(actives);
            }

            if (StringUtil.isNotEmpty(proxy)) {
                providerConfig.setProxy(proxy);
            } else {
                LOG.warn(String.format("the value of '${%s.proxy}' is empty, fixed to default value: '%s'", DubboConstant.PROVIDER_CONFIG_PREFIX, DubboDefaultValue.DEFAULT_COMPILER));
                providerConfig.setProxy(DubboDefaultValue.DEFAULT_COMPILER);
            }

            if (StringUtil.isNotEmpty(cluster)) {
                providerConfig.setCluster(cluster);
            } else {
                LOG.warn(String.format("the value of '${%s.cluster}' is empty, fixed to default value: '%s'", DubboConstant.PROVIDER_CONFIG_PREFIX, DubboDefaultValue.DEFAULT_CLUSTER));
                providerConfig.setCluster(DubboDefaultValue.DEFAULT_CLUSTER);
            }

            if (null != deprecated) {
                providerConfig.setDeprecated(deprecated);
            } else {
                providerConfig.setDeprecated(false);
            }

            if (null != queues) {
                providerConfig.setQueues(queues);
            }

            if (StringUtil.isNotEmpty(charset)) {
                providerConfig.setCharset(charset);
            } else {
                LOG.warn(String.format("the value of '${%s.charset}' is empty, fixed to default value: '%s'", DubboConstant.PROVIDER_CONFIG_PREFIX, DubboDefaultValue.DEFAULT_CHARSET));
                providerConfig.setCharset(DubboDefaultValue.DEFAULT_CHARSET);
            }

            if (null != buffer) {
                providerConfig.setBuffer(buffer);
            } else {
                LOG.warn(String.format("the value of '${%s.buffer}' is empty, fixed to default value: '%s'", DubboConstant.PROVIDER_CONFIG_PREFIX, DubboDefaultValue.DEFAULT_BUFFER));
                providerConfig.setBuffer(DubboDefaultValue.DEFAULT_BUFFER);
            }

            if (null != iothreads) {
                providerConfig.setIothreads(iothreads);
            } else {
                LOG.warn(String.format("the value of '${%s.iothreads}' is empty, fixed to default value: '%s'", DubboConstant.PROVIDER_CONFIG_PREFIX, DubboDefaultValue.DEFAULT_IOTHREAD_SIZE));
                providerConfig.setIothreads(DubboDefaultValue.DEFAULT_IOTHREAD_SIZE);
            }

            if (StringUtil.isNotEmpty(telnet)) {
                providerConfig.setTelnet(telnet);
            }

            if (StringUtil.isNotEmpty(layer)) {
                providerConfig.setLayer(layer);
            } else {
                LOG.warn(String.format("the value of '${%s.layer}' is empty, fixed to default value: '%s'", DubboConstant.PROVIDER_CONFIG_PREFIX, DubboDefaultValue.DEFAULT_SERVER_LAYER));
                providerConfig.setLayer(DubboDefaultValue.DEFAULT_SERVER_LAYER);
            }

            ApplicationConfig applicationConfig = DubboApplicationConfigManager.instance().build(dubboApplicationConfig);
            providerConfig.setApplication(applicationConfig);

            Map<String, RegistryConfig> registryConfig = DubboRegistryConfigManager.instance().build(dubboRegistryConfig);
            if (StringUtil.isNotEmpty(registry)) {//TODO check,这部分实现需要测试
                String[] rs = registry.split(",");
                if (rs.length == 1) {
                    RegistryConfig cf = registryConfig.get(registry);
                    if (null == cf) {
                        LOG.error(String.format("this registry is not exists, '${%s.registry}' : '%s'", DubboConstant.PROVIDER_CONFIG_PREFIX, registry));
                        System.exit(-1);
                    }
                    providerConfig.setRegistry(cf);
                } else {//向用户配置的注册中心注册服务
                    List<RegistryConfig> ls = new ArrayList<>();
                    for (String r : rs) {
                        RegistryConfig cf = registryConfig.get(r);
                        if (null == cf) {
                            LOG.error(String.format("this registry is not exists, '${%s.registry}' : '%s'", DubboConstant.PROVIDER_CONFIG_PREFIX, r));
                            System.exit(-1);
                        }
                        ls.add(cf);
                    }
                    providerConfig.setRegistries(ls);
                }
            } else {//向所有注册中心注册服务
                List<RegistryConfig> ls = new ArrayList<>();
                ls.addAll(registryConfig.values());
                providerConfig.setRegistries(ls);
            }

            if (StringUtil.isNotEmpty(id)) {//ID不做默认设置
                providerConfig.setId(id);
            }
            this.config = providerConfig;
        }
        return this.config;
    }
}
