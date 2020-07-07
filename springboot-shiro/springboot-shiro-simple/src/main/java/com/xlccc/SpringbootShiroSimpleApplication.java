package com.xlccc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SpringbootShiroSimpleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootShiroSimpleApplication.class, args);
    }

}
