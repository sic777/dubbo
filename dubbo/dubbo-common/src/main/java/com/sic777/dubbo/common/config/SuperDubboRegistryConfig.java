package com.sic777.dubbo.common.config;

import com.sic777.utils.proguard.NoProguard;

import java.util.List;

/**
 * <p>注册中心配置</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-04-24
 */
@NoProguard
public abstract class SuperDubboRegistryConfig {
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
}
