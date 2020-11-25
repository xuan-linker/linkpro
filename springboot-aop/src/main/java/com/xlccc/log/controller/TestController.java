package com.xlccc.log.controller;

import com.xlccc.log.annotation.SysLogAnnotation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Linker
 * @date 2020/11/4 9:58
 * @description：
 */
@Controller
@RequestMapping(value = "/test/v1")
public class TestController {

    @SysLogAnnotation("切面测试")
    @GetMapping("/test")
    @ResponseBody
    public String test(String name) {
        return name;
    }
}
