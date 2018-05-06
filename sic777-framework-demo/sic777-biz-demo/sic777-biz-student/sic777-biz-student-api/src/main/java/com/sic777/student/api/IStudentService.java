package com.sic777.student.api;

import com.sic777.dubbo.common.Response;
import com.sic777.student.domain.Student;

import java.util.List;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-05-04
 */
public interface IStudentService {
    /**
     * 创建
     *
     * @param student
     * @return
     * @throws Exception
     */
    Response<Student> create(Student student) throws Exception;

    /**
     * 根据id查找
     *
     * @param id
     * @return
     */
    Response<Student> getById(String id);

    /**
     * 查询所有
     *
     * @return
     */
    Response<List<Student>> listAll();

    /**
     * 更新
     *
     * @param student
     * @return
     */
    Response<Student> update(Student student);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    Response<?> delete(String id);
}
