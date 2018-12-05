package com.sic777.demo.server.test;

import com.alibaba.dubbo.config.annotation.Service;
import com.sic777.demo.api.test.ITestService;
import com.sic777.demo.api.test.TestDto;
import com.sic777.dubbo.bean.RpcResponse;
import com.sic777.common.utils.container.ContainerGetter;

import java.util.List;
import java.util.UUID;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-06-05
 */
@Service(stub = "com.sic777.demo.api.test.TestServiceStub")
public class TestServiceImpl implements ITestService {
    @Override
    public RpcResponse<String> create(TestDto testDto) {
        return new RpcResponse<>(UUID.randomUUID().toString());
    }

    @Override
    public RpcResponse<?> delete(String id) {
        return new RpcResponse<>();
    }

    @Override
    public RpcResponse<?> update(TestDto testDto) {
        return new RpcResponse<>();
    }

    @Override
    public RpcResponse<TestDto> get(String id) {
        TestDto dto = new TestDto();
        dto.setId(id);
        dto.setMsg("描述");
        return new RpcResponse<>(dto);
    }

    @Override
    public RpcResponse<List<TestDto>> list() {
        List<TestDto> ls = ContainerGetter.arrayList();
        for (int i = 1; i < 6; i++) {
            TestDto dto = new TestDto();
            dto.setId(UUID.randomUUID().toString());
            dto.setMsg("描述:" + i);
            ls.add(dto);
        }
        return new RpcResponse<>(ls);
    }
}
