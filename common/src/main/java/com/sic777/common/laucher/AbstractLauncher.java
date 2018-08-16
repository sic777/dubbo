package com.sic777.common.laucher;


import com.sic777.common.laucher.processor.IStarterProcessor;

/**
 * <p>启动基类</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-06-11
 */
public abstract class AbstractLauncher {

    /**
     * 启动
     *
     * @param process 启动类处理接口
     * @param hook    关闭钩子
     */
    public final void start(IStarterProcessor process, Thread hook) {
        this.startImpl(process, hook);
    }

    /**
     * 启动
     */
    public final void start() {
        this.startImpl(null, null);
    }

    /**
     * 启动
     *
     * @param process 启动类处理接口
     */
    public final void start(IStarterProcessor process) {
        this.startImpl(process, null);
    }

    /**
     * 启动
     *
     * @param hook 关闭钩子
     */
    public final void start(Thread hook) {
        this.startImpl(null, hook);
    }

    /**
     * 是否为web环境
     *
     * @return
     */
    protected abstract boolean isWebEnvironment();

    /**
     * 子类实现启动
     *
     * @param process
     * @param hook
     */
    abstract void startImpl(IStarterProcessor process, Thread hook);

}
