package org.cooze.spring.boot.mybatis.datasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.TransactionIsolationLevel;
import org.apache.ibatis.transaction.TransactionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;


/**
 * @author cooze
 * @version 1.0.0
 * @desc
 * @date 2017/9/20
 */
@Configuration
@MapperScan(basePackages = "org.cooze.spring.boot.mybatis.mapper.school", sqlSessionTemplateRef = "schoolSqlSessionTemplate")
public class SchoolDataSourceConfigure {

    @Bean(name = "schoolDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.school")
    @Primary
    public DataSource schoolDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "schoolSqlSessionFactory")
    @Primary
    public SqlSessionFactory schoolSqlSessionFactory(@Qualifier(value = "schoolDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:**/mapper/school/*.xml"));
        return bean.getObject();
    }

    /**
     * 在多数据源的情况下，事务注解需要带上值，如果不带上值，
     * 事务注解会带上默认值transactionManager，
     * 如果在bean池里找不到则事务对象不，或者事务对象不是当前操作事务则事务不生效
     * 例如：@Transactional(value = "cityTransactionManager")
     * @param dataSource
     * @return
     */
    @Bean(name = "schoolTransactionManager")
    @Primary
    public DataSourceTransactionManager schoolTransactionManager(@Qualifier(value = "schoolDataSource") DataSource dataSource) {

        return new DataSourceTransactionManager(dataSource);
    }


    @Bean(name = "schoolSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate schoolSqlSessionTemplate(@Qualifier(value = "schoolSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
