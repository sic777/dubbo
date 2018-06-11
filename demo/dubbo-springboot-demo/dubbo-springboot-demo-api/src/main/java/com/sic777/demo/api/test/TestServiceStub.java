package com.sic777.demo.api.test;

import com.sic777.dubbo.bean.RpcResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-06-05
 */
public class TestServiceStub implements ITestService {
    private ITestService testService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public TestServiceStub(ITestService testService) {
        this.testService = testService;
    }

    @Override
    public RpcResponse<String> create(TestDto testDto) {
        logger.info("create:执行本地存根...");
        return testService.create(testDto);
    }

    @Override
    public RpcResponse<?> delete(String id) {
        logger.info("delete:执行本地存根...");
        return testService.delete(id);
    }

    @Override
    public RpcResponse<?> update(TestDto testDto) {
        logger.info("update:执行本地存根...");
        return testService.update(testDto);
    }

    @Override
    public RpcResponse<TestDto> get(String id) {
        logger.info("get:执行本地存根...");
        return testService.get(id);
    }

    @Override
    public RpcResponse<List<TestDto>> list() {
        logger.info("list:执行本地存根...");
        return testService.list();
    }
}
