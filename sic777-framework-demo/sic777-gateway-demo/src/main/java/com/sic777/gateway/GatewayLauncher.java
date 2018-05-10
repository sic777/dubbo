package com.sic777.gateway;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.sic777.dubbo.comsumer.DubboConsumerLauncher;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-05-03
 */
@SpringBootApplication
@DubboComponentScan("com.jan")
@ComponentScan("com.jan")
public class GatewayLauncher {
    public static void main(String[] args) {
        DubboConsumerLauncher.start(new Thread(() -> {
            System.out.println("优雅关闭");
        }));
    }
}
