package com.xlccc.mapper;

import com.xlccc.pojo.User2;
import com.xlccc.pojo.User2Example;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
public interface User2Mapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User2 record);

    int insertSelective(User2 record);

    List<User2> selectByExample(User2Example example);

    User2 selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User2 record);

    int updateByPrimaryKey(User2 record);

    int truncated();

}