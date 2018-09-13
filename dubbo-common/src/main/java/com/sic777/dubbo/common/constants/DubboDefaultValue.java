package com.sic777.dubbo.common.constants;

import com.sic777.dubbo.common.enums.*;
import com.sic777.utils.proguard.NoProguard;

/**
 * <p>Dubbo默认值常量</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-05-02
 */
@NoProguard
public class DubboDefaultValue {
    /**
     * 默认编码
     */
    public final static String DEFAULT_CHARSET = "UTF-8";
    /**
     * 默认的日志容器
     */
    public final static String DEFAULT_LOGGER = ApplicationLogType.LOG4J2.getType();
    /**
     * 默认组织
     */
    public final static String DEFAULT_ORGANIZATION = "UNKNOWN-ORGANIZATION";
    /**
     * 默认负责人
     */
    public final static String DEFAULT_OWNER = "UNKNOWN-OWNER";
    /**
     * 默认应用分层
     */
    public final static String DEFAULT_APPLICATION_ARCHITECTURE = "UNKNOWN-ARCHITECTURE";
    /**
     * 默认服务分层
     */
    public final static String DEFAULT_SERVER_LAYER = "UNKNOWN-LAYER";
    /**
     * 默认的字节码编译器
     */
    public final static String DEFAULT_COMPILER = ApplicationCompilerType.JAVASSIST.getType();
    /**
     * 默认zookeeper客户端
     */
    public final static String DEFAULT_ZOOKEEPER_CLIENT = RegistryClientType.CURATOR.getType();
    /**
     * 默认注册中心协议
     */
    public final static String DEFAULT_REGISTER_PROTOCOL = RegistryProtocolType.ZOOKEEPER.getType();
    /**
     * dubbo默认的协议
     */
    public final static String DEFAULT_DUBBO_PROTOCOL = DubboProtocolType.DUBBO.getType();
    /**
     * dubbo默认的协议(枚举类型)
     */
    public final static DubboProtocolType DEFAULT_DUBBO_PROTOCOL_ENUM = DubboProtocolType.DUBBO;
    /**
     * 默认线程池类型
     */
    public final static String DEFAULT_THREAD_POOL = ProtocolThreadPoolType.FIXED.getType();
    /**
     * 默认Dubbo协议的序列化方式
     */
    public final static String DEFAULT_DUBBO_SERIALIZATION = "hessian2";
    /**
     * 默认Rmi协议的序列化方式
     */
    public final static String DEFAULT_RIM_SERIALIZATION = "java";
    /**
     * 默认Http协议的序列化方式
     */
    public final static String DEFAULT_HTTP_SERIALIZATION = "json";
    /**
     * 默认Dubbo协议传输协议
     */
    public final static String DEFAULT_DUBBO_TRANSPORTER = RegistryTransportType.NETTY.getType();
    /**
     * 默认Http协议实现方式
     */
    public final static String DEFAULT_HTTP_TRANSPORTER = "servlet";
    /**
     * 默认Dubbo协议消息派发方式
     */
    public final static String DEFAULT_DUBBO_DISPATCHER = "all";
    /**
     * dubbo默认的协议Id
     */
    public final static String DEFAULT_DUBBO_PROTOCOL_ID = "default_%s_protocol";
    /**
     * 默认注册中心id
     */
    public final static String DEFAULT_REGISTRY_ID = "default_%s_registry";
    /**
     * 服务默认负载方式
     */
    public final static String DEFAULT_LOAD_BALANCE = LoadBalanceType.RANDOM.getType();
    /**
     * 默认集群方式
     */
    public final static String DEFAULT_CLUSTER = ClusterType.FAILOVER.getType();
    /**
     * 网络读写缓冲区大小
     */
    public final static int DEFAULT_BUFFER = 8192;
    /**
     * 默认请求及响应数据包大小限制，单位：字节
     */
    public final static int DEFAULT_PAYLOAD = 88388608;
    /**
     * 默认io线程池大小(固定大小)
     */
    public final static int DEFAULT_IOTHREAD_SIZE = Runtime.getRuntime().availableProcessors() + 1;
    /**
     * 默认服务线程池大小(固定大小)
     */
    public final static int DEFAULT_THREAD_SIZE = 100;
    /**
     * 默认注册中心请求超时时间(毫秒)
     */
    public final static int DEFAULT_REGISTER_TIMEOUT = 5000;
    /**
     * 默认注册中心会话超时时间(毫秒)
     */
    public final static int DEFAULT_REGISTER_SESSION_TIMEOUT = 60000;
    /**
     * 默认环境下(比如开发环境)的默认远程服务调用超时时间(毫秒)
     */
    public final static int DEFAULT_REMOTE_SERVER_TIMEOUT_DEFAULT_ENV = 3000;//3秒钟
    /**
     * 非默认环境下的默认远程服务调用超时时间(毫秒)
     */
    public final static int DEFAULT_REMOTE_SERVER_TIMEOUT_NON_DEFAULT_ENV = 600000;//10分钟
    /**
     * 默认远程服务调用重试次数(不包括第一次调用)
     * 默认:不重试
     */
    public final static int DEFAULT_REMOTE_SERVER_RETRIES = 0;
    /**
     * 延迟注册服务时间(毫秒),设为-1时，表示延迟到Spring容器初始化完成时暴露服务
     */
    public final static int DEFAULT_SERVER_REGISTER_DELAY = 0;
    /**
     * 服务提供者默认的filter
     */
    public final static String DEFAULT_PROVIDER_FILTERS = "exceptionFilterProvider";
    /**
     * 服务消费者默认的filter
     */
    public final static String DEFAULT_CONSUMER_FILTERS = "exceptionFilterConsumer";
}
