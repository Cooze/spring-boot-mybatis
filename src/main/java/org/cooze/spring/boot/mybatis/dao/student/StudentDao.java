package org.cooze.spring.boot.mybatis.dao.student;

import org.apache.ibatis.session.SqlSession;
import org.cooze.spring.boot.mybatis.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author cooze
 * @version 1.0.0
 * @desc
 * @date 2017/9/20
 */
@Repository
public class StudentDao {


    @Autowired
    @Qualifier(value = "studentSqlSessionTemplate")
    private SqlSession session;

    public List<Student> findByName(String name) {
        return this.session.selectList("findByStudentName", name);
    }
}
