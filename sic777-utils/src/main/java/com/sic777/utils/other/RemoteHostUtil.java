package com.sic777.utils.other;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-04-23
 */
public class RemoteHostUtil {
    /**
     * 获取host地址
     *
     * @return
     */
    public static String getHost() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return "";
        }
    }
}
