package com.xlccc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName HelloController
 * @Description TODO
 * @Author Linker
 * @Date 2020/1/18 6:05 PM
 **/
@RestController
public class HelloController {
    @RequestMapping("/")
    public String index() {
        return "Hello World!--docker";
    }
}
