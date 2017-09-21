package org.cooze.spring.boot.mybatis.domain;

import java.io.Serializable;

/**
 * @author cooze
 * @version 1.0.0
 * @desc
 * @date 2017/9/20
 */
public class School implements Serializable {

    private Integer id;
    private String name;
    private Integer rate;
    private String no;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }
}
