package com.xlccc.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.xlccc.common.service.HelloService;
import org.springframework.stereotype.Component;

/**
 * @Author Linker
 * @Date 2020/3/21 6:22 下午
 * @Version 1.0
 */
@Service
@Component
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
