package com.sic777.demo.client;

import com.sic777.common.laucher.SpringWebContainerLauncher;
import com.sic777.common.laucher.processor.IStarterProcessor;
import com.sic777.restful.base.response.ResponseBodyType;
import com.sic777.restful.base.response.ResponseManager;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-06-05
 */
@SpringBootApplication
public class DemoClientLauncher {

    @PostConstruct
    public void init() {
        System.out.println("初始化...");
    }

    public static void main(String[] args) {
        SpringWebContainerLauncher.instance().start(new IStarterProcessor() {
            @Override
            public void before() {
                System.out.println("Spring容器启动前");
                ResponseManager.instance().init(ResponseBodyType.DYNAMIC);
            }

            @Override
            public void after() {
                System.out.println("Spring容器启动后");

            }
        }, new Thread() {
            @Override
            public void run() {
                System.out.println("优雅关闭处理");
            }
        });
    }
}
