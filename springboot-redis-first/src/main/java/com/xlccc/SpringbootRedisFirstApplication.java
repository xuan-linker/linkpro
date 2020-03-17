package com.xlccc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 本地Redis默认密码为admin
 */
@SpringBootApplication
public class SpringbootRedisFirstApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRedisFirstApplication.class, args);
    }

}
