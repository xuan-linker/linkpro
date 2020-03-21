package com.xlccc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Linker
 * @Date 2020/1/18 4:27 PM
 * @Version 1.0
 */
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String hello (){
        return "Hello world!";
    }
}