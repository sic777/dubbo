package com.sic777.student.server;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.jan.student.test.ITestService;
import com.sic777.dubbo.common.Response;
import com.sic777.student.api.IStudentService;
import com.sic777.student.bean.StudentBean;
import com.sic777.student.dao.StudentDao;
import com.sic777.student.domain.Student;
import com.sic777.utils.bean.BeanUtilsExtend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private StudentDao studentDao;

    @Reference(version = "1.0")
    private ITestService test;

    @Transactional
    @Override
    public Response<Student> create(Student student) throws Exception {
        test.a();
        System.out.println("新增数据");
        student.setId(UUID.randomUUID().toString());
        StudentBean bean = new StudentBean();
        BeanUtilsExtend.copyProperties(bean, student);
        studentDao.create(bean);
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
