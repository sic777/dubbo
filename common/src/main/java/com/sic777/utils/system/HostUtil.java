package com.sic777.utils.system;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * <p>Host工具类
 *
 * @author sic777
 * @since 0.0.1
 */
public class HostUtil {
    private HostUtil() {
    }

    /**
     * 获取机器名
     *
     * @return
     */
    public static String getHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            return "";
        }
    }

    /**
     * 获取机器地址
     *
     * @return
     */
    public static String getHostAddress() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return "";
        }
    }
}
