package org.cooze.spring.boot.mybatis.controller;

import org.cooze.spring.boot.mybatis.dao.school.SchoolDao;
import org.cooze.spring.boot.mybatis.domain.School;
import org.cooze.spring.boot.mybatis.mapper.school.JpaSchoolMapper;
import org.cooze.spring.boot.mybatis.mapper.school.SchoolMapper;
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
public class SchoolController {

    @Autowired
    private SchoolDao schoolDao;

    @Autowired
    private SchoolMapper schoolMapper;

    @Autowired
    private JpaSchoolMapper jpaSchoolMapper;


    @GetMapping(value = "/school/mapper")
    public List<School> findByNameMapper(@RequestParam(name = "name") String name) {
        return this.schoolMapper.findBySchoolName(name);
    }

    @GetMapping(value = "/school/dao")
    public List<School> findByNameDao(@RequestParam(name = "name") String name) {
        return this.schoolDao.findByName(name);
    }


    @GetMapping(value = "/school/jpa")
    public List<School> getSchoolList(@RequestParam(name = "name") String name) {
        return this.jpaSchoolMapper.getSchoolList(name);
    }

    @PostMapping(value = "/school/jpa")
    //开启事务
    @Transactional(value = "schoolTransactionManager")
    public School addSchool(@RequestBody School school) {
        this.jpaSchoolMapper.addSchool(school);
        //模拟测试一个事务
        Integer.parseInt("m");

        return school;
    }


}
