package com.sic777.demo.client.controller.test;

import com.sic777.demo.api.test.TestDto;
import com.sic777.demo.client.controller.BaseController;
import com.sic777.demo.client.service.test.ITestService;
import com.sic777.microservice.permission.Permission;
import com.sic777.microservice.response.ResponseManager;
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
     * @apiPermission CORP
     * @api {POST} /tests test
     * @apiVersion 2.0.0
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
    @Permission({USER})
    @PostMapping(TESTS)
    void create(@RequestBody TestDto dto) throws Exception {
        ResponseManager.instance().funcValidateValueNotEmpty(dto.getMsg(), "msg");
        String id = testService.create(dto);
        ResponseManager.instance().successId(this, id);
    }

    @Permission({MEMBER, USER})
    @DeleteMapping(TESTS_ID)
    void delete(@PathVariable String id) throws Exception {
        testService.delete(id);
        ResponseManager.instance().success(this);
    }

    @Permission({USER})
    @PutMapping(TESTS_ID)
    void update(@PathVariable String id, @RequestBody TestDto dto) throws Exception {
        dto.setId(id);
        ResponseManager.instance().funcValidateValueNotEmpty(dto.getMsg(), "msg");
        testService.update(dto);
        ResponseManager.instance().success(this);
    }

    @GetMapping(TESTS_ID)
    void get(@PathVariable String id) throws Exception {
        TestDto dto = testService.get(id);
        ResponseManager.instance().success(this, dto);
    }

    @GetMapping(TESTS)
    void list() throws Exception {
        List<TestDto> ls = testService.list();
        ResponseManager.instance().successCollections(this, ls, 1000);
    }
}
