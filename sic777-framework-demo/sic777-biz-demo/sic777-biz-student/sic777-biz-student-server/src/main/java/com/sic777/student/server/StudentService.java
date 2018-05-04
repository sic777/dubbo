package com.sic777.student.server;

import com.alibaba.dubbo.config.annotation.Service;
import com.sic777.dubbo.common.Response;
import com.sic777.student.api.IStudentService;
import com.sic777.student.domain.Student;

import java.util.List;
import java.util.UUID;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-05-04
 */
@Service(version = "1.0",
        stub = "com.sic777.student.api.stub.StudentServiceStub",
        mock = "com.sic777.student.api.mock.StudentServiceMock",
        owner = "sicJan")
public class StudentService implements IStudentService {
    @Override
    public Response<Student> create(Student student) {
        System.out.println("新增数据");
        student.setId(UUID.randomUUID().toString());
        return new Response<>(student);
    }

    @Override
    public Response<Student> getById(String id) {
        System.out.println("根据id[" + id + "]查找");
        return new Response<>(new Student(id, "小明"));
    }

    @Override
    public Response<List<Student>> listAll() {
        return null;
    }

    @Override
    public Response<Student> update(Student student) {
        return null;
    }

    @Override
    public Response<?> delete(String id) {
        return null;
    }
}
