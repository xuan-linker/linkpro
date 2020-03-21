package com.xlccc.service;

import com.xlccc.pojo.User;

import java.util.List;

/**
 * @Author Linker
 * @Date 2020/2/9 0:48
 * @Version 1.0
 */
public interface UserService {
    User findUserByName(String name) ;

    User findUserJustByName(String name) ;

    List<String> selectPermissionByUserId(Integer id) ;

}
