package com.xlccc.mapper;

import com.xlccc.pojo.User1;
import com.xlccc.pojo.User1Example;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
public interface User1Mapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User1 record);

    int insertSelective(User1 record);

    List<User1> selectByExample(User1Example example);

    User1 selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User1 record);

    int updateByPrimaryKey(User1 record);

    int truncated();
}