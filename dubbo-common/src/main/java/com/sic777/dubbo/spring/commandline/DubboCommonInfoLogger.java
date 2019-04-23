package com.sic777.dubbo.spring.commandline;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.sic777.dubbo.common.config.build.DubboApplicationConfigManager;
import com.sic777.dubbo.common.config.build.DubboRegistryConfigManager;
import com.sic777.dubbo.spring.config.SpringBootDubboApplicationConfig;
import com.sic777.dubbo.spring.config.SpringBootDubboRegistryConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-06-04
 */
@Component
@Order(94)
public class DubboCommonInfoLogger implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(DubboCommonInfoLogger.class);

    @Autowired
    private SpringBootDubboApplicationConfig dubboApplicationConfig;
    @Autowired
    private SpringBootDubboRegistryConfig dubboRegistryConfig;

    @Override
    public void run(String... strings) throws Exception {
        logger.info(">>>>>>>>>  Print Dubbo configuration information   <<<<<<<<<\n");
        logger.info("============================================================");
        logger.info("================  Application configuration =================");
        logger.info("============================================================");
        logger.info("\n" + JSON.toJSONString(DubboApplicationConfigManager.instance().build(dubboApplicationConfig),
                SerializerFeature.PrettyFormat));
        logger.info("============================================================");
        logger.info("=================  Registry configuration ==================");
        logger.info("============================================================");
        logger.info("\n" + JSON.toJSONString(DubboRegistryConfigManager.instance().build(dubboRegistryConfig),
                SerializerFeature.PrettyFormat));
    }
}
