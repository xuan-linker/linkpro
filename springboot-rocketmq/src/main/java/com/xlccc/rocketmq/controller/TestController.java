package com.xlccc.rocketmq.controller;

import com.xlccc.rocketmq.service.SendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Linker
 * @date 2020/11/23 10:03
 * @description：
 */
@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class TestController {
    private final SendService sendService;

    @Value("${rocketmq.topic}")
    private String topic;

    @GetMapping("/message")
    public void message() {
        sendService.sync(topic, "同步发送...");
        sendService.async(topic, "异步发送...");
        sendService.oneWay(topic, "单项发送...");
    }

}
