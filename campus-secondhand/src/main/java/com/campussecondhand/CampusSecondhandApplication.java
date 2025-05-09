package com.campussecondhand;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.campussecondhand.mapper")
@EnableTransactionManagement//开启注解方式的事务管理
public class CampusSecondhandApplication {

    public static void main(String[] args) {
        SpringApplication.run(CampusSecondhandApplication.class, args);
    }

}
