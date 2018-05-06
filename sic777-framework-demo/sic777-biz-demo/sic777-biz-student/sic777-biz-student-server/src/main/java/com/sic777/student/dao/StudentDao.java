package com.sic777.student.dao;

import com.sic777.student.bean.StudentBean;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-05-06
 */
@Mapper
public interface StudentDao {
    @Insert("INSERT INTO test(id, name) VALUES(#{id}, #{name})")
    void create(StudentBean studentBean);
}
