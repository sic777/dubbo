package com.jan;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sic777.dubbo.common.Response;
import com.sic777.gateway.rest.controller.BaseController;
import com.sic777.student.api.IStudentService;
import com.sic777.student.domain.Student;
import org.springframework.web.bind.annotation.*;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-05-03
 */
@RestController
public class TestController extends BaseController {
    @Reference(version = "1.0")
    private IStudentService studentService;

    @PostMapping("/v2/t/a")
    public void create(@RequestBody Student student) throws Exception{
        Response<Student> rs = studentService.create(student);
        super.processRpc(rs);
        rest200(rs.getData());
    }

    @GetMapping("/v2/t/x/{id}")
    public void getById(@PathVariable String id) {
        Response<Student> rs = studentService.getById(id);
        super.processRpc(rs);
        super.rest200(rs.getData());
    }
}
