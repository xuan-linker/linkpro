package com.xlccc.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Linker
 * @date 2021/1/27 23:08
 * @description：
 */
@Configuration
@MapperScan("com.xlccc.mbg.mapper")
public class MyBatisConfig {
}
