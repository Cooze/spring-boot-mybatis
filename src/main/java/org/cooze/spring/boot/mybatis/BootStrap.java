package org.cooze.spring.boot.mybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author cooze
 * @version 1.0.0
 * @desc
 * @date 2017/9/20
 */
@SpringBootApplication
@EnableTransactionManagement

public class BootStrap {

    public static void main(String[] args) {
        SpringApplication.run(BootStrap.class,args);
    }

}
