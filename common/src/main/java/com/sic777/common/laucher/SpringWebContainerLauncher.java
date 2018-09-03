package com.sic777.common.laucher;


import com.sic777.utils.proguard.NoProguard;

/**
 * <p>Spring容器Web端启动类
 *
 * @author sic777
 * @since 0.0.1
 */
@NoProguard
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
