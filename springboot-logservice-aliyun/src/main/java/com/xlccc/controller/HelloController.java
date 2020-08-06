package com.xlccc.controller;

import com.aliyun.openservices.log.exception.LogException;
import com.xlccc.service.LogServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Linker
 * @date 2020/8/6 17:08
 * @description：
 */
@RequestMapping("/hello")
@RestController
public class HelloController {
    @Autowired
    private LogServerService logServerService;
    @GetMapping("/world")
    public String hello() throws LogException {
        logServerService.test();
        return "hello world";
    }
}
