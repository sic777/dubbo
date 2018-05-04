package com.sic777.dubbo.comsumer.commandline;

import com.sic777.utils.other.PrintLogo;
import com.alibaba.dubbo.config.*;
import com.alibaba.dubbo.rpc.Exporter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * <p>Dubbo提供者信息打印</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-04-24
 */
@Component
@Order(10)
@ConditionalOnClass(Exporter.class)
public class DubboConsumerInfoLogger implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(DubboConsumerInfoLogger.class);
    @Autowired
    private ApplicationConfig applicationConfig;
    @Autowired
    private RegistryConfig registryConfig;

    @Override

    public void run(String... strings) throws Exception {
        logger.info(">>>>>>>>>  Print Dubbo configuration information   <<<<<<<<<\n");
        logger.info("============================================================");
        logger.info("================  Application configuration =================");
        logger.info("============================================================");
        logger.info("\n" + JSON.toJSONString(applicationConfig, SerializerFeature.PrettyFormat));
        logger.info("============================================================");
        logger.info("=================  Registry configuration ==================");
        logger.info("============================================================");
        logger.info("\n" + JSON.toJSONString(registryConfig, SerializerFeature.PrettyFormat));
        PrintLogo.logo();
    }
}
