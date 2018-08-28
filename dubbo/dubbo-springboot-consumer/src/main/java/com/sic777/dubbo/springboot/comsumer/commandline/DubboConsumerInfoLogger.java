package com.sic777.dubbo.springboot.comsumer.commandline;

import com.sic777.dubbo.comsumer.common.config.build.DubboConsumerConfigManager;
import com.sic777.dubbo.springboot.common.config.SpringBootDubboApplicationConfig;
import com.sic777.dubbo.springboot.common.config.SpringBootDubboRegistryConfig;
import com.sic777.dubbo.springboot.comsumer.config.SpringBootDubboConsumerConfig;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * <p>Dubbo消费者信息打印</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-04-24
 */
@Component
@Order(2)
public class DubboConsumerInfoLogger implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(DubboConsumerInfoLogger.class);
    @Autowired
    private SpringBootDubboApplicationConfig dubboApplicationConfig;
    @Autowired
    private SpringBootDubboRegistryConfig dubboRegistryConfig;
    @Autowired
    private SpringBootDubboConsumerConfig consumerConfig;

    @Override

    public void run(String... strings) throws Exception {
        logger.info("============================================================");
        logger.info("=============  Default consumer configuration ==============");
        logger.info("============================================================");
        logger.info("\n" + JSON.toJSONString(
                DubboConsumerConfigManager.instance().build(dubboApplicationConfig, dubboRegistryConfig, consumerConfig),
                SerializerFeature.PrettyFormat, SerializerFeature.DisableCircularReferenceDetect));
    }
}
