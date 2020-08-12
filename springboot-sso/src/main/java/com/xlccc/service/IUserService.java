package com.xlccc.service;

import com.xlccc.User;

/**
 * @author Linker
 * @date 2020/8/11 21:30
 * @description：
 */
public interface IUserService {
    User selectUser(String account);
}
