package org.cooze.spring.boot.mybatis.controller;

import org.cooze.spring.boot.mybatis.dao.city.CityDao;
import org.cooze.spring.boot.mybatis.domain.City;
import org.cooze.spring.boot.mybatis.mapper.city.CityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author cooze
 * @version 1.0.0
 * @desc
 * @date 2017/9/20
 */
@RestController
public class CityController {

    @Autowired
    private CityMapper cityMapper;

    @Autowired
    private CityDao cityDao;


    @GetMapping(value = "/city/mapper")
    public List<City> findByStateMapper(@RequestParam(name = "state") String state) {
        return this.cityMapper.findByCityState(state);
    }

    @GetMapping(value = "/city/dao")
    public List<City> findByStateDao(@RequestParam(name = "state") String state) {
        return this.cityDao.findByState(state);
    }

    @PostMapping(value = "/city/dao")
    public String addCity(@RequestBody City city) {
        this.cityDao.addCity(city);
        return "success";
    }

}
