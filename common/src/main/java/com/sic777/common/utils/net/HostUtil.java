package com.sic777.common.utils.net;

import com.sic777.common.utils.proguard.NoProguard;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * <p>Host工具类
 *
 * @author sic777
 * @since 0.0.1
 */
@NoProguard
public class HostUtil {
    private HostUtil() {
    }

    /**
     * 获取机器名
     *
     * @return
     * @throws UnknownHostException
     */
    public static String getHostName() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostName();
    }

    /**
     * 获取机器地址
     *
     * @return
     * @throws UnknownHostException
     */
    public static String getHostAddress() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostAddress();
    }
}
