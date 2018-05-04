package com.jan.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-05-04
 */
@Component("janPrint")
@Order(11)
public class Print implements CommandLineRunner {
    @Override
    public void run(String... strings) throws Exception {
        System.out.println("我是最帅的");
    }
}
