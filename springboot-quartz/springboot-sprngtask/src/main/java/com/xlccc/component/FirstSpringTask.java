package com.xlccc.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author Linker
 * @Date 2020/4/17 10:14 上午
 * @Version 1.0
 * TODO:执行定时任务
 */
@Component
@Slf4j
public class FirstSpringTask {

    @Scheduled(cron = "10/5 * * * * ? ")
    private void HelloTask() {
        log.info("SpringTask -- Hello ");
    }

}
