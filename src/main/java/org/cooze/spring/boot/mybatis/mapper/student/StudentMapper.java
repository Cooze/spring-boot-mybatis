package org.cooze.spring.boot.mybatis.mapper.student;


import org.cooze.spring.boot.mybatis.domain.Student;

import java.util.List;

/**
 * @author cooze
 * @version 1.0.0
 * @desc 这种不是jpa的方式，而是使用mybatis的xml配置文件写sql语句,xml的id必须要和方法名一样
 * 否则会报错，statement 找不到的错。
 * @date 2017/9/20
 */
public interface StudentMapper {
    List<Student> findByStudentName(String name);

    int addStudent(Student student);
}
