package com.xlccc.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.xlccc.User;
import com.xlccc.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * @author Linker
 * @date 2020/8/11 21:32
 * @description：模仿从数据库中读取
 */
@Service
public class UserServiceImpl implements IUserService {

    public static final User user = new User("hello", DigestUtil.md5Hex("123456"));


    @Override
    public User selectUser(String account) {
        if (account.equals("hello")) {
            return user;
        }
        return null;
    }
}
