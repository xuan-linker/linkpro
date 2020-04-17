package com.xlccc.springbootschedule.controller;

import com.xlccc.springbootschedule.service.ScheduleTaskJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author Linker
 * @Date 2020/4/17 9:43 上午
 * @Version 1.0
 */
@Slf4j
@RestController
public class TaskController {
    @Resource
    private Scheduler scheduler;

    @GetMapping(value = "hello")
    public String hello() {
        return "Hello ";
    }

    @GetMapping(value = "exe")
    public String executeTask() {
        JobDetail jobDetail = JobBuilder.newJob(ScheduleTaskJob.class)
                .withIdentity("MyFirstQuartzTest")
//                .usingJobData("msg1", "ScheduleTaskJob")
//                .storeDurably()
                .build();
        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(5)
                .repeatForever();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("quartzTestJob")
                .startNow()
                .withSchedule(simpleScheduleBuilder)
                .build();
        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
            log.error("Error" + e);
        }
        return "success";
    }
}
