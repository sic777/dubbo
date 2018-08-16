package com.sic777.common.laucher;


/**
 * <p>Spring容器服务端启动类</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-06-11
 */
public final class SpringServerContainerLauncher extends SpringContainerLauncher {
    private static SpringServerContainerLauncher singleton = new SpringServerContainerLauncher();

    public static SpringServerContainerLauncher instance() {
        return singleton;
    }

    private SpringServerContainerLauncher() {
    }

    @Override
    protected boolean isWebEnvironment() {
        return false;
    }

}
