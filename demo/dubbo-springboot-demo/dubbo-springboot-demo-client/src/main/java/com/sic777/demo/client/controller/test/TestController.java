package com.sic777.demo.client.controller.test;

import com.sic777.demo.api.test.TestDto;
import com.sic777.demo.client.controller.BaseController;
import com.sic777.demo.client.service.test.ITestService;
import com.sic777.restful.base.permission.Permission;
import com.sic777.restful.base.response.ResponseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.sic777.demo.client.controller.REST_URL.*;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-06-05
 */
@RestController
public class TestController extends BaseController {
    @Autowired
    private ITestService testService;

    /**
     * @apiUse request_body
     * @apiUse dynamic_error
     * @apiUse access_token
     * @apiPermission BIZ
     * @api {POST} /tests 新增
     * @apiVersion 1.0.0
     * @apiGroup test
     * @apiDescription api test
     * @apiParamExample {json} 请求示例
     * {
     * "msg":"信息"
     * }
     * @apiSuccessExample 成功示例
     * {
     * "id":"id"
     * }
     * @apiSuccess (成功说明) {string} id 主键
     */
    @Permission({BIZ})
    @PostMapping(TESTS)
    void create(@RequestBody TestDto dto) throws Exception {
        ResponseManager.instance().funcValidateValueNotEmpty(dto.getMsg(), "msg");
        String id = testService.create(dto);
        ResponseManager.instance().successId(this, id);
    }

    /**
     * @apiUse dynamic_error
     * @apiUse access_token
     * @apiPermission BIZ
     * @apiPermission USER
     * @api {DELETE} /tests/:id 删除
     * @apiParam (请求参数) {string} id 主键
     * @apiVersion 1.0.0
     * @apiGroup test
     * @apiDescription api test
     */
    @Permission({BIZ, USER})
    @DeleteMapping(TESTS_ID)
    void delete(@PathVariable String id) throws Exception {
        testService.delete(id);
        ResponseManager.instance().success(this);
    }

    /**
     * @apiUse dynamic_error
     * @apiUse access_token
     * @apiPermission USER
     * @api {PUT} /tests/:id 更新
     * @apiParam (请求参数) {string} id 主键
     * @apiVersion 1.0.0
     * @apiGroup test
     * @apiDescription api test
     * @apiParamExample {json} 请求示例
     * {
     * "msg":"信息"
     * }
     */
    @Permission({USER})
    @PutMapping(TESTS_ID)
    void update(@PathVariable String id, @RequestBody TestDto dto) throws Exception {
        dto.setId(id);
        ResponseManager.instance().funcValidateValueNotEmpty(dto.getMsg(), "msg");
        testService.update(dto);
        ResponseManager.instance().success(this);
    }

    /**
     * @apiUse dynamic_error
     * @api {GET} /tests/:id 获取
     * @apiParam (请求参数) {string} id 主键
     * @apiVersion 1.0.0
     * @apiGroup test
     * @apiDescription api test
     * @apiSuccessExample 成功示例
     * {
     * "id":"id",
     * "msg":"msg"
     * }
     * @apiSuccess (成功说明) {string} id 主键
     * @apiSuccess (成功说明) {string} msg 信息
     */
    @GetMapping(TESTS_ID)
    void get(@PathVariable String id) throws Exception {
        TestDto dto = testService.get(id);
        ResponseManager.instance().success(this, dto);
    }

    /**
     * @apiUse dynamic_response_list
     * @apiUse dynamic_error
     * @api {GET} /tests 列表
     * @apiVersion 1.0.0
     * @apiGroup test
     * @apiDescription api test
     * @apiSuccess (成功说明) {string} list.id 主键
     * @apiSuccess (成功说明) {string} list.msg 信息
     */
    @GetMapping(TESTS)
    void list() throws Exception {
        List<TestDto> ls = testService.list();
        ResponseManager.instance().successCollections(this, ls, 1000);
    }
}
