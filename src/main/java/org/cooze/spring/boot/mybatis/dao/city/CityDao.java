package org.cooze.spring.boot.mybatis.dao.city;

import org.apache.ibatis.session.SqlSession;
import org.cooze.spring.boot.mybatis.domain.City;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author cooze
 * @version 1.0.0
 * @desc
 * @date 2017/9/21
 */
@Repository
public class CityDao {

    @Autowired
    @Qualifier(value = "citySqlSessionTemplate")
    private SqlSession session;

    public List<City> findByState(String state) {
        return this.session.selectList("findByCityState", state);
    }

    /**
     *
     * @param city
     * @return
     */
    @Transactional(value = "cityTransactionManager")
    public int addCity(City city) {
        int i = this.session.insert("addCity", city);
        Integer.parseInt("t");
        return i;
    }
}
