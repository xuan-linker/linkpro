package com.xlccc.mapper;

import com.xlccc.model.User;
import org.apache.ibatis.annotations.Param;

/**
 * @Author Linker
 * @Date 2020/3/27 7:51 下午
 * @Version 1.0
 */
public interface UserMapper {
    User findByUsername(@Param("username") String username);
}
