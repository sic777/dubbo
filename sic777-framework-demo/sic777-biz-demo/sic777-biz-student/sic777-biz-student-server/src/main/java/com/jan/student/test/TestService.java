package com.jan.student.test;

import com.alibaba.dubbo.config.annotation.Service;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-05-04
 */
@Service(version = "1.0",
        owner = "sicJan")
public class TestService implements ITestService {

    @Override
    public void a() {

    }
}
