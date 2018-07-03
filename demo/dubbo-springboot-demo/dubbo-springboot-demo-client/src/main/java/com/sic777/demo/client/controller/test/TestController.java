package com.sic777.demo.client.controller.test;

import com.alibaba.fastjson.JSONObject;
import com.sic777.demo.api.test.TestDto;
import com.sic777.demo.client.controller.BaseController;
import com.sic777.demo.client.service.test.ITestService;
import com.sic777.microservice.permission.Permission;
import com.sic777.microservice.response.ResponseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * @param dto
     * @throws Exception
     * @apiUse common
     * @apiVersion 1.0.0
     * @apiGroup test
     * @api {post} /test 新增
     * @apiDescription 新增一条数据
     * @apiUse ack
     * @apiPermission BIZ
     * @apiParam (请求参数) {json} requestBody 请求体
     * @apiExample {json} 请求体示例:
     * { "msg":"信息" }
     * @apiSuccessExample {json} 成功响应体示例:
     * { "id":"主键" }
     * @apiSuccess (成功响应体说明) {string} id 主键
     */
    @Permission(BIZ)
    @PostMapping("/test")
    void create(@RequestBody TestDto dto) throws Exception {
        String id = testService.create(dto);
        JSONObject retJson = ResponseManager.instance().generateReturnId("id", id);
        super.rest200(retJson);
    }

    /**
     * @param id
     * @throws Exception
     * @apiUse common
     * @apiVersion 1.0.0
     * @apiGroup test
     * @api {delete} /test/:id 删除
     * @apiDescription 删除一条数据
     * @apiUse ack
     * @apiPermission BIZ
     * @apiPermission ADMIN
     * @apiParam (请求参数) {string} id id
     */
    @Permission({BIZ, ADMIN})
    @DeleteMapping("/test/{id}")
    void delete(@PathVariable String id) throws Exception {
        testService.delete(id);
    }

    /**
     * @param id
     * @param dto
     * @throws Exception
     * @apiUse common
     * @apiVersion 1.0.0
     * @apiGroup test
     * @api {put} /test/:id 更新
     * @apiDescription 更新一条数据
     * @apiUse ack
     * @apiPermission ADMIN
     * @apiParam (请求参数) {string} id id
     * @apiParam (请求参数) {json} requestBody 请求体
     * @apiExample {json} 请求体示例:
     * {
     * "msg":"信息"
     * }
     */
    @Permission({ADMIN})
    @PutMapping("/test/{id}")
    void update(@PathVariable String id, @RequestBody TestDto dto) throws Exception {
        dto.setId(id);
        testService.update(dto);
    }


    /**
     * @param id
     * @throws Exception
     * @apiUse common
     * @apiVersion 1.0.0
     * @apiGroup test
     * @api {get} /test/:id 查找
     * @apiDescription 根据ID查找一条数据
     * @apiParam (请求参数) {string} id 主键
     * @apiSuccessExample {json} 成功响应体示例:
     * {
     * "id":"主键",
     * "msg":"信息"
     * }
     * @apiSuccess (成功响应体说明) {string} id 主键
     * @apiSuccess (成功响应体说明) {string} msg 信息
     */
    @GetMapping("/test/{id}")
    void get(@PathVariable String id) throws Exception {
        TestDto dto = testService.get(id);
        super.rest200(dto);
    }

    /**
     * @throws Exception
     * @apiUse common
     * @apiVersion 1.0.0
     * @apiGroup test
     * @api {get} /tests 列表
     * @apiDescription 查找列表
     * @apiSuccessExample {json} 成功响应体示例:
     * {
     * "list":
     * [{
     * "id":"主键",
     * "msg":"信息"
     * },
     * {
     * "id":"主键",
     * "msg":"信息"
     * }],
     * "count":100
     * }
     * @apiSuccess (成功响应体说明) {array} list 列表数组
     * @apiSuccess (成功响应体说明) {string} list.id 主键
     * @apiSuccess (成功响应体说明) {string} list.msg 信息
     * @apiSuccess (成功响应体说明) {int} count 总数
     */
    @GetMapping("/tests")
    void list() throws Exception {
        List<TestDto> ls = testService.list();
        JSONObject retJson = ResponseManager.instance().generateReturnList(ls, 1000);
        super.rest200(retJson);
    }
}
