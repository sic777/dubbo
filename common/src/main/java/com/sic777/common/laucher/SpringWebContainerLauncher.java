package com.sic777.common.laucher;


/**
 * <p>Spring容器Web端启动类
 *
 * @author sic777
 * @since 0.0.1
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
