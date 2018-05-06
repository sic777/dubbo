package com.sic777.student.api.stub;

import com.sic777.common.error.ERROR;
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
public class StudentServiceStub implements IStudentService {

    private final IStudentService studentService;

    public StudentServiceStub(IStudentService studentService) {
        this.studentService = studentService;
    }

    @SuppressWarnings("unchecked")
	@Override
    public Response<Student> create(Student student) {
        try {
            System.out.println("参数校验");
            if (student == null) {
                Response.bizException(ERROR.PARAM_INVALID.getCode(), String.format(ERROR.PARAM_INVALID.getMsg(), "id"));
            }
            return studentService.create(student);
        } catch (Exception e) {
            System.out.println("容错");
            return Response.exception(e);
        }
    }

    @Override
    public Response<Student> getById(String id) {
        System.out.println("参数校验");
        return studentService.getById(id);
    }

    @Override
    public Response<List<Student>> listAll() {
        return studentService.listAll();
    }

    @Override
    public Response<Student> update(Student student) {
        return studentService.update(student);
    }

    @Override
    public Response<?> delete(String id) {
        return studentService.delete(id);
    }
}
