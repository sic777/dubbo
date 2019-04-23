package com.sic777.dubbo.provider.common.config;


/**
 * <p>服务提供者缺省配置</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-04-24
 */
public abstract class SuperDubboProviderConfig {

    /**
     * 协议BeanId，可以在<dubbo:service proivder="">中引用此ID
     * 默认:[dubbo]
     */
    private String id;
    /**
     * 协议名称
     * 默认:[dubbo]
     */
    private String protocol;
    /**
     * 服务主机名，多网卡选择或指定VIP及域名时使用，为空则自动查找本机IP，建议不要配置，让Dubbo自动获取本机IP
     */
    private String host;
    /**
     * 服务线程池大小(固定大小)
     * 默认:[100]
     */
    private Integer threads;
    /**
     * 请求及响应数据包大小限制，单位：字节
     * 默认:[88388608(=8M)]
     */
    private Integer payload;
    /**
     * 提供者上下文路径，为服务path的前缀
     */
    private String path;
    /**
     * 协议的服务器端实现类型，比如：dubbo协议的mina,netty等，http协议的jetty,servlet等
     * 默认dubbo协议缺省为netty，http协议缺省为servlet
     */
    private String server;
    /**
     * 协议的客户端实现类型，比如：dubbo协议的mina,netty等
     * dubbo协议缺省为netty
     */
    private String client;
    /**
     * 协议编码方式
     */
    private String codec;
    /**
     * 协议序列化方式，当协议支持多种序列化方式时使用，比如：dubbo协议的dubbo,hessian2,java,compactedjava，以及http协议的json,xml等
     * 默认:[dubbo]
     */
    private String serialization;
    /**
     * 服务提供方远程调用过程拦截器名称，多个名称用逗号分隔
     * 默认:[false]
     */
    private String filter;
    /**
     * 服务提供方导出服务监听器名称，多个名称用逗号分隔
     */
    private String listener;
    /**
     * 线程池类型，可选：fixed/cached
     * 默认[fixed]
     */
    private String threadpool;
    /**
     * 服务提供者最大可接受连接数
     */
    private Integer accepts;
    /**
     * 服务版本，建议使用两位数字版本，如：1.0，通常在接口不兼容时版本号才需要升级
     * 默认:[0.0.0]
     */
    private String version;
    /**
     * 服务分组，当一个接口有多个实现，可以用分组区分
     */
    private String group;
    /**
     * 延迟注册服务时间(毫秒)- ，设为-1时，表示延迟到Spring容器初始化完成时暴露服务
     * 默认:[0]
     */
    private Integer delay;
    /**
     * 远程服务调用超时时间(毫秒)
     * 默认:[1000]
     */
    private Integer timeout;
    /**
     * 远程服务调用重试次数，不包括第一次调用，不需要重试请设为0
     * 默认:[2]
     */
    private Integer retries;
    /**
     * 对每个提供者的最大连接数，rmi、http、hessian等短连接协议表示限制连接数，dubbo等长连接协表示建立的长连接个数
     */
    private Integer connections;
    /**
     * 负载均衡策略，可选值：random,roundrobin,leastactive，分别表示：随机，轮循，最少活跃调用
     * 默认:[random]
     */
    private String loadbalance;
    /**
     * 是否缺省异步执行，不可靠异步，只是忽略返回值，不阻塞执行线程
     * 默认:[false]
     */
    private Boolean async;
    /**
     * 设为true，表示使用缺省代理类名，即：接口名 + Local后缀。
     * 默认:[false]
     */
    private Boolean stub;
    /**
     * 设为true，表示使用缺省Mock类名，即：接口名 + Mock后缀。
     * 默认:[false]
     */
    private Boolean mock;
    /**
     * 令牌验证，为空表示不开启，如果为true，表示随机生成动态令牌
     * 默认:[false]
     */
    private Boolean token;
    /**
     * 向指定注册中心注册，在多个注册中心时使用，值为<dubbo:registry>的id属性，多个注册中心ID用逗号分隔，如果不想将该服务注册到任何registry，可将值设为N/A
     * 缺省向所有registry注册
     */
    private String registry;
    /**
     * 服务是否动态注册，如果设为false，注册后将显示后disable状态，需人工启用，并且服务提供者停止时，也不会自动取消册，需人工禁用。
     * 默认:[true]
     */
    private Boolean dynamic;
    /**
     * 设为true，将向logger中输出访问日志，也可填写访问日志文件路径，直接把访问日志输出到指定文件
     * 默认:[false]
     */
    private String accesslog;
    /**
     * 服务负责人，用于服务治理，请填写负责人公司邮箱前缀
     */
    private String owner;
    /**
     * 服务文档URL
     */
    private String document;
    /**
     * 服务权重
     */
    private Integer weight;
    /**
     * 服务提供者每服务每方法最大可并行执行请求数
     */
    private Integer executes;
    /**
     * 每服务消费者每服务每方法最大并发调用数
     */
    private Integer actives;
    /**
     * 生成动态代理方式，可选：jdk/javassist
     * 默认:[javassist]
     */
    private String proxy;
    /**
     * 集群方式，可选：failover/failfast/failsafe/failback/forking
     * 默认:[failover]
     */
    private String cluster;
    /**
     * 服务是否过时，如果设为true，消费方引用时将打印服务过时警告error日志
     * 默认:[false]
     */
    private Boolean deprecated;
    /**
     * 线程池队列大小，当线程池满时，排队等待执行的队列大小，建议不要设置，当线程程池时应立即失败，重试其它服务提供机器，而不是排队，除非有特殊需求。
     */
    private Integer queues;
    /**
     * 序列化编码
     * 默认:[UTF-8]
     */
    private String charset;
    /**
     * 网络读写缓冲区大小
     * 默认:[8192]
     */
    private Integer buffer;
    /**
     * IO线程池，接收网络读写中断，以及序列化和反序列化，不处理业务，业务线程池参见threads配置，此线程池和CPU相关，不建议配置。
     * 默认:[CPU + 1]
     */
    private Integer iothreads;
    /**
     * 所支持的telnet命令，多个命令用逗号分隔
     */
    private String telnet;
    /**
     * 服务提供者所在的分层。如：biz、dao、intl:web、china:acton。
     */
    private String layer;

    public String getId() {
        return id;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getHost() {
        return host;
    }

    public Integer getThreads() {
        return threads;
    }

    public Integer getPayload() {
        return payload;
    }

    public String getPath() {
        return path;
    }

    public String getServer() {
        return server;
    }

    public String getClient() {
        return client;
    }

    public String getCodec() {
        return codec;
    }

    public String getSerialization() {
        return serialization;
    }


    public String getFilter() {
        return filter;
    }

    public String getListener() {
        return listener;
    }

    public String getThreadpool() {
        return threadpool;
    }

    public Integer getAccepts() {
        return accepts;
    }

    public String getVersion() {
        return version;
    }

    public String getGroup() {
        return group;
    }

    public Integer getDelay() {
        return delay;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public Integer getRetries() {
        return retries;
    }

    public Integer getConnections() {
        return connections;
    }

    public String getLoadbalance() {
        return loadbalance;
    }

    public Boolean getAsync() {
        return async;
    }

    public Boolean getStub() {
        return stub;
    }

    public Boolean getMock() {
        return mock;
    }

    public Boolean getToken() {
        return token;
    }

    public String getRegistry() {
        return registry;
    }

    public Boolean getDynamic() {
        return dynamic;
    }

    public String getAccesslog() {
        return accesslog;
    }

    public String getOwner() {
        return owner;
    }

    public String getDocument() {
        return document;
    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getExecutes() {
        return executes;
    }

    public Integer getActives() {
        return actives;
    }

    public String getProxy() {
        return proxy;
    }

    public String getCluster() {
        return cluster;
    }

    public Boolean getDeprecated() {
        return deprecated;
    }

    public Integer getQueues() {
        return queues;
    }

    public String getCharset() {
        return charset;
    }

    public Integer getBuffer() {
        return buffer;
    }

    public Integer getIothreads() {
        return iothreads;
    }

    public String getTelnet() {
        return telnet;
    }

    public String getLayer() {
        return layer;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setThreads(Integer threads) {
        this.threads = threads;
    }

    public void setPayload(Integer payload) {
        this.payload = payload;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public void setCodec(String codec) {
        this.codec = codec;
    }

    public void setSerialization(String serialization) {
        this.serialization = serialization;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public void setListener(String listener) {
        this.listener = listener;
    }

    public void setThreadpool(String threadpool) {
        this.threadpool = threadpool;
    }

    public void setAccepts(Integer accepts) {
        this.accepts = accepts;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setDelay(Integer delay) {
        this.delay = delay;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public void setRetries(Integer retries) {
        this.retries = retries;
    }

    public void setConnections(Integer connections) {
        this.connections = connections;
    }

    public void setLoadbalance(String loadbalance) {
        this.loadbalance = loadbalance;
    }

    public void setAsync(Boolean async) {
        this.async = async;
    }

    public void setStub(Boolean stub) {
        this.stub = stub;
    }

    public void setMock(Boolean mock) {
        this.mock = mock;
    }

    public void setToken(Boolean token) {
        this.token = token;
    }

    public void setRegistry(String registry) {
        this.registry = registry;
    }

    public void setDynamic(Boolean dynamic) {
        this.dynamic = dynamic;
    }

    public void setAccesslog(String accesslog) {
        this.accesslog = accesslog;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public void setExecutes(Integer executes) {
        this.executes = executes;
    }

    public void setActives(Integer actives) {
        this.actives = actives;
    }

    public void setProxy(String proxy) {
        this.proxy = proxy;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public void setDeprecated(Boolean deprecated) {
        this.deprecated = deprecated;
    }

    public void setQueues(Integer queues) {
        this.queues = queues;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public void setBuffer(Integer buffer) {
        this.buffer = buffer;
    }

    public void setIothreads(Integer iothreads) {
        this.iothreads = iothreads;
    }

    public void setTelnet(String telnet) {
        this.telnet = telnet;
    }

    public void setLayer(String layer) {
        this.layer = layer;
    }

}
