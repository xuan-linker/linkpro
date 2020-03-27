package com.xlccc.service;

import com.xlccc.model.User;

/**
 * @Author Linker
 * @Date 2020/3/27 7:51 下午
 * @Version 1.0
 */
public interface UserService {
    User findByUsername(String username);
}
