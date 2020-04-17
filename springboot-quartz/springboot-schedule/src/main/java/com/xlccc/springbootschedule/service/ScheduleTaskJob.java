package com.xlccc.springbootschedule.service;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @Author Linker
 * @Date 2020/4/17 9:42 上午
 * @Version 1.0
 */
@Slf4j
public class ScheduleTaskJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("Schedule--Job Run...");
    }
}
