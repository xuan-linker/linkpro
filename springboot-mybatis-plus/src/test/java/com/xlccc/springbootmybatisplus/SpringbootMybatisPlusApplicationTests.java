package com.xlccc.springbootmybatisplus;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xlccc.mapper.UserMapper;
import com.xlccc.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootMybatisPlusApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }
    @Test
    public void testSelectOne(){
        User user = userMapper.selectById(1);
        System.out.println(user);
    }
    @Test
    public void testInsert(){
        User user = new User();
        user.setName("Linkp");
        user.setAge(1);
        user.setEmail("xlccc.com");
        assertThat(userMapper.insert(user)).isGreaterThan(0);
        assertThat(user.getId()).isNotNull();
    }

    @Test
    public void testDelete(){
        assertThat(userMapper.deleteById(3L)).isGreaterThan(0);
        QueryWrapper wrapper = new QueryWrapper();
        assertThat(userMapper.delete(new QueryWrapper<User>()
                .lambda().eq(User::getName,"Jack"))).isGreaterThan(0);
    }
    @Test
    public void testUpdate(){
//		User user = userMapper.selectById(2);
//		assertThat(user.getAge()).isEqualTo(20);
//		assertThat(user.getName()).isEqualTo("Jack");


    }

}
