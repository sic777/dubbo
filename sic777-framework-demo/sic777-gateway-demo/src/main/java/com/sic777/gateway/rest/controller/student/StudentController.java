package com.sic777.gateway.rest.controller.student;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sic777.dubbo.common.Response;
import com.sic777.gateway.rest.REST_URL;
import com.sic777.gateway.rest.controller.BaseController;
import com.sic777.student.api.IStudentService;
import com.sic777.student.domain.Student;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-05-03
 */
@RestController
public class StudentController extends BaseController {
    @Reference(version = "1.0")
    private IStudentService studentService;

    @ApiOperation(value = "测试", httpMethod = "POST", notes = "测试接口", response = Student.class)
    @PostMapping(REST_URL.TEST)
    public void create(@RequestBody Student student)throws Exception{
        Response<Student> rs = studentService.create(student);
        super.processRpc(rs);
        rest200(rs.getData());
    }

    @GetMapping(REST_URL.TEST_ID)
    public void getById(@PathVariable String id) {
        Response<Student> rs = studentService.getById(id);
        super.processRpc(rs);
        super.rest200(rs.getData());
    }
}
