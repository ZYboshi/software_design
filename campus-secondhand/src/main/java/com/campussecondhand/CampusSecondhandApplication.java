package com.campussecondhand;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.campussecondhand.mapper")
public class CampusSecondhandApplication {

    public static void main(String[] args) {
        SpringApplication.run(CampusSecondhandApplication.class, args);
    }

}
