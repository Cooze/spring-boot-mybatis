package org.cooze.spring.boot.mybatis.controller;

import org.cooze.spring.boot.mybatis.dao.student.StudentDao;
import org.cooze.spring.boot.mybatis.domain.Student;
import org.cooze.spring.boot.mybatis.mapper.student.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author cooze
 * @version 1.0.0
 * @desc
 * @date 2017/9/20
 */
@RestController
public class StudentController {

    @Autowired
    protected StudentDao studentDao;

    @Autowired
    private StudentMapper studentMapper;


    @GetMapping(value = "/student/mapper")
    public List<Student> findByNameMapper(@RequestParam(name = "name") String name) {
        return this.studentMapper.findByStudentName(name);
    }

    @PostMapping(value = "/student/mapper")
    @Transactional(value = "studentTransactionManager")
    public String findByNameMapper(@RequestBody Student student) {
        this.studentMapper.addStudent(student);
        //制造异常，测试事务，结果是生效
        Integer.parseInt("w");
        return "success!";
    }

    @GetMapping(value = "/student/dao")
    public List<Student> findByNameDao(@RequestParam(name = "name") String name) {
        return this.studentDao.findByName(name);
    }
}
