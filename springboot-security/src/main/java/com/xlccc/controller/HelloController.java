package com.xlccc.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Linker
 * @Date 2020/3/26 10:13 下午
 * @Version 1.0
 */
@RestController
public class HelloController {
    /**
     * 所有用户都可以访问
     * @return
     */
    @RequestMapping("/")
    public String Home() {
        return "Hello Home";
    }

    /**
     * ADMIN用户登录后可访问
     * @return
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/hello")
    public String Hello() {
        return "Hello World";
    }
}
