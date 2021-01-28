package com.xlccc.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Linker
 * @date 2021/1/28 13:50
 * @description：
 */
@Configuration
@MapperScan({"com.xlccc.mbg.mapper","com.xlccc.dao"})
public class MyBatisConfig {
}
