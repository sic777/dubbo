package com.sic777.dubbo.spring.commandline;

import com.sic777.common.utils.system.PrintLogo;
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
@Order(99)
public class LogoInfoLogger implements CommandLineRunner {
    @Override
    public void run(String... strings) throws Exception {
        PrintLogo.logo();
    }
}
