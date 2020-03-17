package com.xlccc.mapper;

import com.xlccc.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @ClassName UserMapper
 * @Description TODO
 * @Author Linker
 * @Date 2020/1/15 3:57 PM
 **/
@Mapper
public interface UserMapper {

    @Select("select * from user_")
    List<User> findAll();

    @Insert(" insert into user_ ( name ) values (#{name}) ")
    public int save(User user);

    @Delete(" delete from user_ where id= #{id} ")
    public void delete(int id);

    @Select("select * from user_ where id= #{id} ")
    public User get(int id);

    @Update("update user_ set name=#{name} where id=#{id} ")
    public int update(User user);

}
