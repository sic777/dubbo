package com.sic777.demo.client.service.test;

import com.sic777.demo.api.test.TestDto;
import com.sic777.dubbo.bean.RpcResponse;
import com.sic777.restful.base.response.ResponseManager;
import com.sic777.restful.base.utils.RpcResponseUtil;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>封装多一层Service是为了在此处做更多的业务逻辑：比如调用多个原子服务提供向Web层组合服务</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-06-05
 */
@Component
public class TestServiceImpl implements ITestService {
    @Reference
    private com.sic777.demo.api.test.ITestService testService;

    @Override
    public String create(TestDto testDto) throws Exception {
        //通用异常测试
        ResponseManager.instance().throwResourceNotFoundException("测试资源不存在");
        RpcResponse<String> response = testService.create(testDto);
        RpcResponseUtil.check(response);
        return response.getData();
    }

    @Override
    public void delete(String id) throws Exception {
        RpcResponse<?> response = testService.delete(id);
        RpcResponseUtil.check(response);
    }

    @Override
    public void update(TestDto testDto) throws Exception {
        RpcResponse<?> response = testService.update(testDto);
        RpcResponseUtil.check(response);
    }

    @Override
    public TestDto get(String id) throws Exception {
        RpcResponse<TestDto> response = testService.get(id);
        RpcResponseUtil.check(response);
        return response.getData();
    }

    @Override
    public List<TestDto> list() throws Exception {
        RpcResponse<List<TestDto>> response = testService.list();
        RpcResponseUtil.check(response);
        return response.getData();
    }
}
