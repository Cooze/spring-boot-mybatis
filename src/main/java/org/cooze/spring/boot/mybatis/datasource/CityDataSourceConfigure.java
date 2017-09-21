package org.cooze.spring.boot.mybatis.datasource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;


/**
 * @author cooze
 * @version 1.0.0
 * @desc 使用c3p0作为mysql的连接池
 * @date 2017/9/20
 */
@Configuration
@MapperScan(basePackages = "org.cooze.spring.boot.mybatis.mapper.city", sqlSessionTemplateRef = "citySqlSessionTemplate")
public class CityDataSourceConfigure {

    @Value("${spring.datasource.city.username}")
    private String username;

    @Value("${spring.datasource.city.password}")
    private String password;

    @Value("${spring.datasource.city.driver-class-name}")
    private String mysqlDriver;

    @Value("${spring.datasource.city.url}")
    private String url;

    //当连接池中的连接耗尽的时候c3p0一次同时获取的连接数
    @Value("${spring.datasource.city.acquireIncrement}")
    private int acquireIncrement;

    //初始化时获取十个连接，取值应在minPoolSize与maxPoolSize之间
    @Value("${spring.datasource.city.initialPoolSize}")
    private int initialPoolSize;

    @Value("${spring.datasource.city.minPoolSize}")
    private int minPoolSize;

    @Value("${spring.datasource.city.maxPoolSize}")
    private int maxPoolSize;

    @Bean(name = "cityDataSource")
    public DataSource cityDataSource() {

        ComboPooledDataSource dataSource = new ComboPooledDataSource();

        dataSource.setUser(username);
        dataSource.setPassword(password);
        dataSource.setJdbcUrl(url);
        try {
            dataSource.setDriverClass(mysqlDriver);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }

        dataSource.setAcquireIncrement(acquireIncrement);
        dataSource.setMaxPoolSize(maxPoolSize);
        dataSource.setMinPoolSize(minPoolSize);
        dataSource.setInitialPoolSize(initialPoolSize);

        return dataSource;
    }

    @Bean(name = "citySqlSessionFactory")
    public SqlSessionFactory citySqlSessionFactory(@Qualifier(value = "cityDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);

        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:**/mapper/city/*.xml"));
        return bean.getObject();
    }

    /**
     * 在多数据源的情况下由于不同数据源对应不同的事务，
     * 所以在使用事务注解的时候需要带上对应的数据源事务注解值，如果不带上值，
     * 事务注解会带上默认值transactionManager，
     * 如果在bean池里找不到则事务对象不，或者事务对象不是当前操作事务则事务不生效
     * 例如：@Transactional(value = "cityTransactionManager")
     *
     * @param dataSource
     * @return
     */
    @Bean(name = "cityTransactionManager")
    public DataSourceTransactionManager cityTransactionManager(@Qualifier(value = "cityDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "citySqlSessionTemplate")
    public SqlSessionTemplate citySqlSessionTemplate(@Qualifier(value = "citySqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
