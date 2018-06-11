package com.sic777.microservice.commandline;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-05-04
 */
@Component
@Order(3)
public class MicroServerLogger implements CommandLineRunner {
    @Autowired
    private ServerProperties serverProperties;

    private static final Logger logger = LoggerFactory.getLogger(MicroServerLogger.class);

    @Override
    public void run(String... strings) throws Exception {
        logger.info("============================================================");
        logger.info("===============  Web container configuration ===============");
        logger.info("============================================================");
        logger.info("\n" + JSON.toJSONString(serverProperties, SerializerFeature.PrettyFormat, SerializerFeature.DisableCircularReferenceDetect));
    }
}
