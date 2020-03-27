package com.xlccc.service.impl;

import com.xlccc.mapper.UserMapper;
import com.xlccc.model.User;
import com.xlccc.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author Linker
 * @Date 2020/3/27 7:52 下午
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }
}
