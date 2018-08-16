package com.sic777.common.laucher;

/**
 * <p>Spring容器Web端启动类</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-06-11
 */
public final class SpringWebContainerLauncher extends SpringContainerLauncher {
    private static SpringWebContainerLauncher singleton = new SpringWebContainerLauncher();

    public static SpringWebContainerLauncher instance() {
        return singleton;
    }

    private SpringWebContainerLauncher() {
    }

    @Override
    protected boolean isWebEnvironment() {
        return true;
    }
}
