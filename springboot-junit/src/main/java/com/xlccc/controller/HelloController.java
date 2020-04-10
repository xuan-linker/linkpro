package com.xlccc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Linker
 * @Date 2020/4/10 10:44 上午
 * @Version 1.0
 */
@RestController
public class HelloController {
    @GetMapping(value = "/get")
    public String get() {
        return "Hello GetMethod ";
    }

    @PostMapping(value = "/post")
    public String post() {
        return "Hello PostMethod";
    }
}
