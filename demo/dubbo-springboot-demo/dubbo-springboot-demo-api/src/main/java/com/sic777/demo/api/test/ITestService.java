package com.sic777.demo.api.test;

import com.sic777.dubbo.bean.RpcResponse;

import java.util.List;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-06-05
 */
public interface ITestService {
    /**
     * 增
     *
     * @param testDto
     * @return
     */
    RpcResponse<String> create(TestDto testDto);

    /**
     * 删
     *
     * @param id
     * @return
     */
    RpcResponse<?> delete(String id);

    /**
     * 改
     *
     * @param testDto
     * @return
     */
    RpcResponse<?> update(TestDto testDto);

    /**
     * 查
     *
     * @param id
     * @return
     */
    RpcResponse<TestDto> get(String id);

    /**
     * 列表
     *
     * @return
     */
    RpcResponse<List<TestDto>> list();
}
