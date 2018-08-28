package com.sic777.dubbo.provider.common.config;

import java.util.List;

/**
 * <p>服务提供者协议配置</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-04-24
 */
public abstract class SuperDubboProtocolConfig {
    /**
     * 协议BeanId，可以在<dubbo:service protocol="">中引用此ID，如果ID不填，缺省和name属性值一样，重复则在name后加序号。
     */
    private List<String> id;
    /**
     * [必填]
     * 协议名称，默认值"dubbo"
     */
    private List<String> name;
    /**
     * <p>
     * 服务端口:
     * dubbo协议缺省端口为20880，rmi协议缺省端口为1099，http和hessian协议缺省端口为80；
     * 如果配置为-1 或者 没有配置port，则会分配一个没有被占用的端口。
     * Dubbo 2.4.0+，分配的端口在协议缺省端口的基础上增长，确保端口段可控。
     * </p>
     */
    private List<Integer> port;
    /**
     * <p>
     * -服务主机名，多网卡选择或指定VIP及域名时使用，为空则自动查找本机IP，
     * -建议不要配置，让Dubbo自动获取本机IP
     * </p>
     */
    private List<String> host;
    /**
     * 线程池类型，可选：fixed/cached，默认fixed
     */
    private List<String> threadpool;
    /**
     * 服务线程池大小(固定大小)，默认为100
     */
    private List<Integer> threads;
    /**
     * io线程池大小(固定大小),默认为cpu个数+1
     */
    private List<Integer> iothreads;
    /**
     * 服务提供方最大可接受连接数,默认值为0,表示不限制
     */
    private List<Integer> accepts;
    /**
     * 请求及响应数据包大小限制，单位：字节，默认88388608(=8M)
     */
    private List<Integer> payload;
    /**
     * 协议编码方式
     */
    private List<String> codec;
    /**
     * <p>
     * 协议序列化方式，当协议支持多种序列化方式时使用，比如：dubbo协议的dubbo,hessian2,java,compactedjava，以及http协议的json等
     * dubbo协议缺省为hessian2，rmi协议缺省为java，http协议缺省为json
     * </p>
     */
    private List<String> serialization;
    /**
     * 1.设为"true"，将向logger中输出访问日志，
     * 2."填写访问日志文件路径"，直接把访问日志输出到指定文件
     */
    private List<String> accesslog;
    /**
     * 提供者上下文路径，为服务path的前缀
     */
    private List<String> path;
    /**
     * <p>
     * 协议的服务端和客户端实现类型，比如：dubbo协议的mina,netty等，可以分拆为server和client配置
     * dubbo协议缺省为netty
     * </p>
     */
    private List<String> transporter;
    /**
     * <p>
     * 协议的服务器端实现类型，比如：dubbo协议的mina,netty等，http协议的jetty,servlet等
     * dubbo协议缺省为netty，http协议缺省为servlet
     * </p>
     */
    private List<String> server;
    /**
     * <p>
     * 协议的客户端实现类型，比如：dubbo协议的mina,netty等
     * dubbo协议缺省为netty
     * </p>
     */
    private List<String> client;
    /**
     * <p>
     * 协议的消息派发方式，用于指定线程模型，比如：dubbo协议的all, direct, message, execution, connection等
     * dubbo协议缺省为all
     * </p>
     */
    private List<String> dispatcher;
    /**
     * <p>
     * 线程池队列大小，当线程池满时，排队等待执行的队列大小，建议不要设置，当线程程池时应立即失败，重试其它服务提供机器，而不是排队，除非有特殊需求。
     * </p>
     */
    private List<Integer> queues;
    /**
     * 序列化编码
     * 默认UTF-8
     */
    private List<String> charset;
    /**
     * 网络读写缓冲区大小
     * 默认8192
     */
    private List<Integer> buffer;
    /**
     * 心跳间隔，对于长连接，当物理层断开时，比如拔网线，TCP的FIN消息来不及发送，对方收不到断开事件，此时需要心跳来帮助检查连接是否已断开
     */
    private List<Integer> heartbeat;
    /**
     * 所支持的telnet命令，多个命令用逗号分隔
     */
    private List<String> telnet;
    /**
     * 该协议的服务是否注册到注册中心,默认为true
     */
    private List<Boolean> register;
    /**
     * 上下文路径，缺省为空串
     */
    private List<String> contextpath;

    public List<String> getId() {
        return id;
    }

    public void setId(List<String> id) {
        this.id = id;
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

    public List<Integer> getPort() {
        return port;
    }

    public void setPort(List<Integer> port) {
        this.port = port;
    }

    public List<String> getHost() {
        return host;
    }

    public void setHost(List<String> host) {
        this.host = host;
    }

    public List<String> getThreadpool() {
        return threadpool;
    }

    public void setThreadpool(List<String> threadpool) {
        this.threadpool = threadpool;
    }

    public List<Integer> getThreads() {
        return threads;
    }

    public void setThreads(List<Integer> threads) {
        this.threads = threads;
    }

    public List<Integer> getIothreads() {
        return iothreads;
    }

    public void setIothreads(List<Integer> iothreads) {
        this.iothreads = iothreads;
    }

    public List<Integer> getAccepts() {
        return accepts;
    }

    public void setAccepts(List<Integer> accepts) {
        this.accepts = accepts;
    }

    public List<Integer> getPayload() {
        return payload;
    }

    public void setPayload(List<Integer> payload) {
        this.payload = payload;
    }

    public List<String> getCodec() {
        return codec;
    }

    public void setCodec(List<String> codec) {
        this.codec = codec;
    }

    public List<String> getSerialization() {
        return serialization;
    }

    public void setSerialization(List<String> serialization) {
        this.serialization = serialization;
    }

    public List<String> getAccesslog() {
        return accesslog;
    }

    public void setAccesslog(List<String> accesslog) {
        this.accesslog = accesslog;
    }

    public List<String> getPath() {
        return path;
    }

    public void setPath(List<String> path) {
        this.path = path;
    }

    public List<String> getTransporter() {
        return transporter;
    }

    public void setTransporter(List<String> transporter) {
        this.transporter = transporter;
    }

    public List<String> getServer() {
        return server;
    }

    public void setServer(List<String> server) {
        this.server = server;
    }

    public List<String> getClient() {
        return client;
    }

    public void setClient(List<String> client) {
        this.client = client;
    }

    public List<String> getDispatcher() {
        return dispatcher;
    }

    public void setDispatcher(List<String> dispatcher) {
        this.dispatcher = dispatcher;
    }

    public List<Integer> getQueues() {
        return queues;
    }

    public void setQueues(List<Integer> queues) {
        this.queues = queues;
    }

    public List<String> getCharset() {
        return charset;
    }

    public void setCharset(List<String> charset) {
        this.charset = charset;
    }

    public List<Integer> getBuffer() {
        return buffer;
    }

    public void setBuffer(List<Integer> buffer) {
        this.buffer = buffer;
    }

    public List<Integer> getHeartbeat() {
        return heartbeat;
    }

    public void setHeartbeat(List<Integer> heartbeat) {
        this.heartbeat = heartbeat;
    }

    public List<String> getTelnet() {
        return telnet;
    }

    public void setTelnet(List<String> telnet) {
        this.telnet = telnet;
    }

    public List<Boolean> getRegister() {
        return register;
    }

    public void setRegister(List<Boolean> register) {
        this.register = register;
    }

    public List<String> getContextpath() {
        return contextpath;
    }

    public void setContextpath(List<String> contextpath) {
        this.contextpath = contextpath;
    }
}
