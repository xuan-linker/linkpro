package com.xlccc.service;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Service;

/**
 * @author Linker
 * @Date 2020/2/8 23:42
 */
@Service
public class ShiroSampleService {

    @RequiresPermissions("read")
    public String read() {
        return "reading...";
    }

    @RequiresPermissions("write")
    public String write() {
        return "writting...";
    }
}