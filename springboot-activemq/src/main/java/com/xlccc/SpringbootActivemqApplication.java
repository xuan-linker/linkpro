package com.xlccc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

/**
 * @author Linker
 * @date 1/14/2022 4:03 PM
 * @description：
 */
@SpringBootApplication
@EnableJms//启动消息队列
public class SpringbootActivemqApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootActivemqApplication.class, args);
    }
}
