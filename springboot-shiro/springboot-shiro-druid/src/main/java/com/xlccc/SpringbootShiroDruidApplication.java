package com.xlccc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
@MapperScan(basePackages = {"com.xlccc.mapper"})
public class SpringbootShiroDruidApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootShiroDruidApplication.class, args);
    }

}
