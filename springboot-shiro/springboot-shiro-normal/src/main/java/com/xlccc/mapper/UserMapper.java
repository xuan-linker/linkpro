package com.xlccc.mapper;

import com.xlccc.pojo.User;

import java.util.List;
/**
 * @Author Linker
 * @Date 2020/2/9 0:45
 * @Version 1.0
 */
public interface UserMapper {
    User findUserByName(String name) ;

    User findUserJustByName(String name) ;

    List<String> selectPermissionByUserId(Integer id) ;
}
