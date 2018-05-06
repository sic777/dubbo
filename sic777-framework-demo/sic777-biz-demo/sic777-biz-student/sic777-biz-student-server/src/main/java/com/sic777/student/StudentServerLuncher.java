package com.sic777.student;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.sic777.dubbo.provider.DubboProviderLuncher;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

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
@EnableTransactionManagement
public class StudentServerLuncher {
    public static void main(String[] args) {
        DubboProviderLuncher.start(new Thread(() -> {
            System.out.println("优雅关闭");
        }));
    }
}
