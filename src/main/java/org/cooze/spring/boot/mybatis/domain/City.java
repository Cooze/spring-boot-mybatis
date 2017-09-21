package org.cooze.spring.boot.mybatis.domain;

import java.io.Serializable;

/**
 * @author cooze
 * @version 1.0.0
 * @desc
 * @date 2017/9/20
 */
public class City implements Serializable {

    private Long id;

    private String name;

    private String state;

    private String country;

    public City() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
