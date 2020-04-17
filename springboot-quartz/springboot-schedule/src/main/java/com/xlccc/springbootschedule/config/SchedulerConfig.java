package com.xlccc.springbootschedule.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @Author Linker
 * @Date 2020/4/17 9:38 上午
 * @Version 1.0
 * TODO：Quertz调度工程配置
 */
@Configuration
public class SchedulerConfig {

    @Bean(name = "scheduler")
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        // 用于quartz集群,QuartzScheduler 启动时更新己存在的Job
        bean.setOverwriteExistingJobs(true);
        // 延时一秒启动
        bean.setStartupDelay(1);
        return bean;
    }
}
