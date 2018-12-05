package com.sic777.common.laucher;


import com.sic777.common.utils.proguard.NoProguard;

/**
 * <p>Spring容器服务端启动类
 *
 * @author sic777
 * @since 0.0.1
 */
@NoProguard
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
