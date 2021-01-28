package com.xlccc.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.xlccc.dao"})
public class MyBatisConfig {
}
