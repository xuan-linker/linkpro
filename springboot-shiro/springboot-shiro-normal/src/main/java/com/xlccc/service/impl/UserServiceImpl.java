package com.xlccc.service.impl;

import com.xlccc.mapper.UserMapper;
import com.xlccc.pojo.User;
import com.xlccc.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Linker
 * @Date 2020/3/21 5:18 下午
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public User findUserByName(String name) {
        return userMapper.findUserByName(name);
    }

    @Override
    public User findUserJustByName(String name) {
        return userMapper.findUserJustByName(name);
    }

    @Override
    public List<String> selectPermissionByUserId(Integer id) {
        return userMapper.selectPermissionByUserId(id);
    }
}
