package com.xlccc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.xlccc.mapper")
@SpringBootApplication
public class SpringbootShiroNormalApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootShiroNormalApplication.class, args);
    }

}
