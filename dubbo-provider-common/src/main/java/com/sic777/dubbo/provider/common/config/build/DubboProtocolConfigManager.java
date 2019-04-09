package com.sic777.dubbo.provider.common.config.build;

import com.sic777.dubbo.common.constants.DubboConstant;
import com.sic777.dubbo.common.constants.DubboDefaultValue;
import com.sic777.dubbo.common.enums.DubboProtocolType;
import com.sic777.dubbo.common.enums.ProtocolThreadPoolType;
import com.sic777.dubbo.common.enums.RegistryTransportType;
import com.sic777.dubbo.provider.common.config.SuperDubboProtocolConfig;
import com.sic777.common.utils.lang.StringUtil;
import org.apache.dubbo.config.ProtocolConfig;
import com.sic777.common.utils.proguard.NoProguard;
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
@NoProguard
public class DubboProtocolConfigManager {
    private static DubboProtocolConfigManager singleton = new DubboProtocolConfigManager();

    public static DubboProtocolConfigManager instance() {
        return singleton;
    }

    private DubboProtocolConfigManager() {
    }


    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    private AtomicBoolean isBuild = new AtomicBoolean(false);
    private Map<String, ProtocolConfig> configMap;

    /**
     * 构造协议配置
     *
     * @param dubboProtocolConfig
     * @return
     */
    @SuppressWarnings("deprecation")
	public final Map<String, ProtocolConfig> build(SuperDubboProtocolConfig dubboProtocolConfig) {
        if (isBuild.compareAndSet(false, true)) {
            Map<String, ProtocolConfig> protocolConfigMap = new HashMap<>();
            List<String> name = dubboProtocolConfig.getName();
            List<String> id = dubboProtocolConfig.getId();
            List<Integer> port = dubboProtocolConfig.getPort();
            List<String> host = dubboProtocolConfig.getHost();
            List<String> threadpool = dubboProtocolConfig.getThreadpool();
            List<Integer> threads = dubboProtocolConfig.getThreads();
            List<Integer> iothreads = dubboProtocolConfig.getIothreads();
            List<Integer> accepts = dubboProtocolConfig.getAccepts();
            List<Integer> payload = dubboProtocolConfig.getPayload();
            List<String> codec = dubboProtocolConfig.getCodec();
            List<String> serialization = dubboProtocolConfig.getSerialization();
            List<String> accesslog = dubboProtocolConfig.getAccesslog();
            List<String> path = dubboProtocolConfig.getPath();
            List<String> transporter = dubboProtocolConfig.getTransporter();
            List<String> server = dubboProtocolConfig.getServer();
            List<String> client = dubboProtocolConfig.getClient();
            List<String> dispatcher = dubboProtocolConfig.getDispatcher();
            List<String> charset = dubboProtocolConfig.getCharset();
            List<Integer> queues = dubboProtocolConfig.getQueues();
            List<Integer> buffer = dubboProtocolConfig.getBuffer();
            List<Integer> heartbeat = dubboProtocolConfig.getHeartbeat();
            List<String> telnet = dubboProtocolConfig.getTelnet();
            List<Boolean> register = dubboProtocolConfig.getRegister();
            List<String> contextpath = dubboProtocolConfig.getContextpath();

            if (null == name || name.isEmpty()) {
                LOG.error(String.format("the value of '${%s.name}' must not be empty!", DubboConstant.PROTOCOL_CONFIG_PREFIX));
                System.exit(-1);
            }

            int size = name.size();
            if (size > 1 && (id == null || id.isEmpty())) {//如果存在多协议,则id必须不能为空
                LOG.error(String.format("the value of '${%s.id}' must not be empty!", DubboConstant.PROTOCOL_CONFIG_PREFIX));
                System.exit(-1);
            }
            if (id != null && id.size() < size) {
                LOG.error(String.format("the length of '${%s.id}' must be greater than or equal to the '${%s.name}'", DubboConstant.PROTOCOL_CONFIG_PREFIX, DubboConstant.PROTOCOL_CONFIG_PREFIX));
                System.exit(-1);
            }

            for (int i = 0; i < size; i++) {
                ProtocolConfig protocolConfig = new ProtocolConfig();
                String nameValue = name.get(i);
                DubboProtocolType protocolType = DubboProtocolType.fromType(nameValue);
                if (protocolType == DubboProtocolType.UNKNOWN) {
                    LOG.warn(String.format("the value of '${%s.name}' is unknown, current value : '%s', fixed to default value: '%s'", DubboConstant.PROTOCOL_CONFIG_PREFIX, nameValue, DubboDefaultValue.DEFAULT_DUBBO_PROTOCOL));
                    protocolConfig.setName(DubboDefaultValue.DEFAULT_DUBBO_PROTOCOL);
                } else {
                    protocolConfig.setName(nameValue);
                }

                String idValue;
                if ((id == null || id.isEmpty()) && size == 1) {
                    String DEFAULT_ID = String.format(DubboDefaultValue.DEFAULT_DUBBO_PROTOCOL_ID, nameValue);
                    LOG.warn(String.format("the value of '${%s.id}' is empty, fixed to default value: '%s'", DubboConstant.PROTOCOL_CONFIG_PREFIX, DEFAULT_ID));
                    protocolConfig.setId(DEFAULT_ID);
                    idValue = DEFAULT_ID;
                } else {
                    protocolConfig.setId(id.get(i));
                    idValue = id.get(i);
                }

                if (null != port && port.get(i) != null) {
                    protocolConfig.setPort(port.get(i));
                }

                if (null != host && StringUtil.isNotEmpty(host.get(i))) {
                    protocolConfig.setHost(host.get(i));
                }

                if (null == threadpool || threadpool.isEmpty()) {
                    LOG.warn(String.format("the value of '${%s.threadpool}' is empty, fixed to default value: '%s'", DubboConstant.PROTOCOL_CONFIG_PREFIX, DubboDefaultValue.DEFAULT_THREAD_POOL));
                    protocolConfig.setThreadpool(DubboDefaultValue.DEFAULT_THREAD_POOL);
                } else {
                    String threadPoolValue = threadpool.get(i);
                    ProtocolThreadPoolType threadPoolType = ProtocolThreadPoolType.fromType(threadPoolValue);
                    if (threadPoolType == ProtocolThreadPoolType.UNKNOWN) {
                        LOG.warn(String.format("the value of '${%s.threadpool}' is unknown, current value : '%s', fixed to default value: '%s'", DubboConstant.PROTOCOL_CONFIG_PREFIX, threadPoolValue, DubboDefaultValue.DEFAULT_THREAD_POOL));
                        protocolConfig.setThreadpool(DubboDefaultValue.DEFAULT_THREAD_POOL);
                    } else {
                        protocolConfig.setThreadpool(threadPoolValue);
                    }
                }

                if (null != threads && threads.get(i) != null) {
                    protocolConfig.setThreads(threads.get(i));
                } else {
                    LOG.warn(String.format("the value of '${%s.threads}' is empty, fixed to default value: '%s'", DubboConstant.PROTOCOL_CONFIG_PREFIX, DubboDefaultValue.DEFAULT_THREAD_SIZE));
                    protocolConfig.setThreads(DubboDefaultValue.DEFAULT_THREAD_SIZE);
                }

                if (null != iothreads && iothreads.get(i) != null) {
                    protocolConfig.setIothreads(iothreads.get(i));
                } else {
                    LOG.warn(String.format("the value of '${%s.iothreads}' is empty, fixed to default value: '%s'", DubboConstant.PROTOCOL_CONFIG_PREFIX, DubboDefaultValue.DEFAULT_IOTHREAD_SIZE));
                    protocolConfig.setIothreads(DubboDefaultValue.DEFAULT_IOTHREAD_SIZE);
                }

                if (null != accepts && accepts.get(i) != null) {
                    protocolConfig.setAccepts(accepts.get(i));
                }

                if (null != payload && payload.get(i) != null) {
                    protocolConfig.setPayload(payload.get(i));
                } else {
                    LOG.warn(String.format("the value of '${%s.payload}' is empty, fixed to default value: '%s'", DubboConstant.PROTOCOL_CONFIG_PREFIX, DubboDefaultValue.DEFAULT_PAYLOAD));
                    protocolConfig.setPayload(DubboDefaultValue.DEFAULT_PAYLOAD);
                }

                if (null != codec && StringUtil.isNotEmpty(codec.get(i))) {
                    protocolConfig.setCodec(codec.get(i));
                }

                if (null != serialization && StringUtil.isNotEmpty(serialization.get(i))) {
                    protocolConfig.setSerialization(serialization.get(i));
                } else {
                    String DEFAULT_SERIALIZATION = protocolType == DubboProtocolType.DUBBO
                            ? DubboDefaultValue.DEFAULT_DUBBO_SERIALIZATION
                            : (protocolType == DubboProtocolType.RMI
                            ? DubboDefaultValue.DEFAULT_RIM_SERIALIZATION
                            : DubboDefaultValue.DEFAULT_HTTP_SERIALIZATION);
                    protocolConfig.setSerialization(DEFAULT_SERIALIZATION);
                    LOG.warn(String.format("the value of '${%s.serialization}' is empty, fixed to default value: '%s'", DubboConstant.PROTOCOL_CONFIG_PREFIX, DEFAULT_SERIALIZATION));
                }

                if (null != accesslog && StringUtil.isNotEmpty(accesslog.get(i))) {
                    protocolConfig.setAccesslog(accesslog.get(i));
                }

                if (null != path && StringUtil.isNotEmpty(path.get(i))) {
                    protocolConfig.setPath(path.get(i));
                }

                if (null == transporter || transporter.isEmpty()) {
                    LOG.warn(String.format("the value of '${%s.transporter}' is empty, fixed to default value: '%s'", DubboConstant.PROTOCOL_CONFIG_PREFIX, DubboDefaultValue.DEFAULT_DUBBO_TRANSPORTER));
                    protocolConfig.setTransporter(DubboDefaultValue.DEFAULT_DUBBO_TRANSPORTER);
                } else {
                    String trans = transporter.get(i);
                    RegistryTransportType transportType = RegistryTransportType.fromType(trans);
                    if (transportType == RegistryTransportType.UNKNOWN) {
                        LOG.warn(String.format("the value of '${%s.transporter}' is unknown, current value : '%s', fixed to default value: '%s'", DubboConstant.PROTOCOL_CONFIG_PREFIX, trans, DubboDefaultValue.DEFAULT_DUBBO_TRANSPORTER));
                        protocolConfig.setTransporter(DubboDefaultValue.DEFAULT_DUBBO_TRANSPORTER);
                    } else {
                        protocolConfig.setTransporter(transportType.getType());
                    }
                }

                if (null != server && StringUtil.isNotEmpty(server.get(i))) {
                    protocolConfig.setServer(server.get(i));
                } else {
                    String DEFAULT_TRANSPORTER = protocolType == DubboProtocolType.DUBBO
                            ? DubboDefaultValue.DEFAULT_DUBBO_TRANSPORTER : DubboDefaultValue.DEFAULT_HTTP_TRANSPORTER;
                    protocolConfig.setTransporter(DEFAULT_TRANSPORTER);
                    LOG.warn(String.format("the value of '${%s.server}' is empty, fixed to default value: '%s'", DubboConstant.PROTOCOL_CONFIG_PREFIX, DEFAULT_TRANSPORTER));
                }

                if (null != client && StringUtil.isNotEmpty(client.get(i))) {
                    protocolConfig.setClient(client.get(i));
                } else {
                    if (protocolType == DubboProtocolType.DUBBO) {
                        protocolConfig.setClient(DubboDefaultValue.DEFAULT_DUBBO_TRANSPORTER);
                        LOG.warn(String.format("the value of '${%s.client}' is empty, fixed to default value: '%s'", DubboConstant.PROTOCOL_CONFIG_PREFIX, DubboDefaultValue.DEFAULT_DUBBO_TRANSPORTER));
                    }
                }

                if (null != dispatcher && StringUtil.isNotEmpty(dispatcher.get(i))) {
                    protocolConfig.setDispatcher(dispatcher.get(i));
                } else {
                    if (protocolType == DubboProtocolType.DUBBO) {
                        protocolConfig.setDispatcher(DubboDefaultValue.DEFAULT_DUBBO_DISPATCHER);
                        LOG.warn(String.format("the value of '${%s.dispatcher}' is empty, fixed to default value: '%s'", DubboConstant.PROTOCOL_CONFIG_PREFIX, DubboDefaultValue.DEFAULT_DUBBO_DISPATCHER));
                    }
                }

                if (null != charset && StringUtil.isNotEmpty(charset.get(i))) {
                    protocolConfig.setCharset(charset.get(i));
                } else {
                    protocolConfig.setCharset(DubboDefaultValue.DEFAULT_CHARSET);
                    LOG.warn(String.format("the value of '${%s.charset}' is empty, fixed to default value: '%s'", DubboConstant.PROTOCOL_CONFIG_PREFIX, DubboDefaultValue.DEFAULT_CHARSET));
                }

                if (null != queues && queues.get(i) != null) {
                    protocolConfig.setQueues(queues.get(i));
                }

                if (null != buffer && buffer.get(i) != null) {
                    protocolConfig.setBuffer(buffer.get(i));
                } else {
                    protocolConfig.setBuffer(DubboDefaultValue.DEFAULT_BUFFER);
                    LOG.warn(String.format("the value of '${%s.buffer}' is empty, fixed to default value: '%s'", DubboConstant.PROTOCOL_CONFIG_PREFIX, DubboDefaultValue.DEFAULT_BUFFER));
                }

                if (null != heartbeat && heartbeat.get(i) != null) {
                    protocolConfig.setHeartbeat(heartbeat.get(i));
                }

                if (null != telnet && StringUtil.isNotEmpty(telnet.get(i))) {
                    protocolConfig.setTelnet(telnet.get(i));
                }

                if (null != register && register.get(i) != null) {
                    protocolConfig.setRegister(register.get(i));
                } else {
                    protocolConfig.setRegister(true);
                }
                if (null != contextpath && StringUtil.isNotEmpty(contextpath.get(i))) {
                    protocolConfig.setContextpath(contextpath.get(i));
                }

                protocolConfigMap.put(idValue, protocolConfig);
            }
            this.configMap = protocolConfigMap;
        }
        return this.configMap;
    }
}
