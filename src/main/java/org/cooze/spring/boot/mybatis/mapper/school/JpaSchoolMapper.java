package org.cooze.spring.boot.mybatis.mapper.school;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.cooze.spring.boot.mybatis.domain.School;

import java.util.List;

/**
 * @author xianzhe_song
 * @version 1.0.0
 * @desc 这种jpa方式不写mybatis的xml配置，直接在方法上写注解，
 * 因为在配置数据源的时候已经配置了扫描地址所以会扫描这歌包下所有的接口，
 * 如果没有sql注解则会找XML配置中的id来匹配接口中的方法。
 * @date 2017/9/21
 */

public interface JpaSchoolMapper {

    @Select("select * from school where name like '%'||#{name}||'%'")
    List<School> getSchoolList(@Param(value = "name") String name);


    @Insert("insert into school(name, no, rate) values(#{name},#{no},#{rate})")
    void addSchool(School school);

}
