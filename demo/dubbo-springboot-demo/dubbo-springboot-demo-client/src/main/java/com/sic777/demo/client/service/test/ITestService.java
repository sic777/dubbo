package com.sic777.demo.client.service.test;

import com.sic777.demo.api.test.TestDto;

import java.util.List;

/**
 * <p>封装多一层Service是为了在此处做更多的业务逻辑：比如调用多个原子服务提供向Web层组合服务</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-06-05
 */
public interface ITestService {

    String create(TestDto testDto) throws Exception;

    void delete(String id) throws Exception;

    void update(TestDto testDto) throws Exception;

    TestDto get(String id) throws Exception;

    List<TestDto> list() throws Exception;
}
