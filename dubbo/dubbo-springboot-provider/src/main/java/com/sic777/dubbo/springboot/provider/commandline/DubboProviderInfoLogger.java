package com.sic777.dubbo.springboot.provider.commandline;

import com.sic777.dubbo.provider.common.config.build.DubboProtocolConfigManager;
import com.sic777.dubbo.provider.common.config.build.DubboProviderConfigManager;
import com.sic777.dubbo.springboot.common.config.SpringBootDubboApplicationConfig;
import com.sic777.dubbo.springboot.common.config.SpringBootDubboRegistryConfig;
import com.sic777.dubbo.springboot.provider.config.SpringBootDubboProtocolConfig;
import com.sic777.dubbo.springboot.provider.config.SpringBootDubboProviderConfig;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
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
@Order(2)
public class DubboProviderInfoLogger implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(DubboProviderInfoLogger.class);
    @Autowired
    private SpringBootDubboApplicationConfig dubboApplicationConfig;
    @Autowired
    private SpringBootDubboRegistryConfig dubboRegistryConfig;
    @Autowired
    private SpringBootDubboProtocolConfig dubboProtocolConfig;
    @Autowired
    private SpringBootDubboProviderConfig dubboProviderConfig;

    @Override
    public void run(String... strings) throws Exception {
        logger.info("============================================================");
        logger.info("=================  Protocol configuration ==================");
        logger.info("============================================================");
        logger.info("\n" + JSON.toJSONString(DubboProtocolConfigManager.instance().build(dubboProtocolConfig),
                SerializerFeature.PrettyFormat));
        logger.info("============================================================");
        logger.info("=============  Default provider configuration ==============");
        logger.info("============================================================");
        logger.info("\n" + JSON.toJSONString(
                DubboProviderConfigManager.instance().build(dubboApplicationConfig, dubboRegistryConfig, dubboProtocolConfig, dubboProviderConfig),
                SerializerFeature.PrettyFormat, SerializerFeature.DisableCircularReferenceDetect));
    }
}
