package com.sic777.student.api.mock;

import com.sic777.dubbo.common.Response;
import com.sic777.student.api.IStudentService;
import com.sic777.student.domain.Student;

import java.util.List;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-05-04
 */
public class StudentServiceMock implements IStudentService {
    @Override
    public Response<Student> create(Student student) {
        return Response.rpcException();
    }

    @Override
    public Response<Student> getById(String id) {
        return Response.rpcException();
    }

    @Override
    public Response<List<Student>> listAll() {
        return Response.rpcException();
    }

    @Override
    public Response<Student> update(Student student) {
        return Response.rpcException();
    }

    @Override
    public Response<?> delete(String id) {
        return Response.rpcException();
    }
}
