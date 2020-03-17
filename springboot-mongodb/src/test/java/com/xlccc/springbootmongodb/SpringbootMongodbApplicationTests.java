package com.xlccc.springbootmongodb;

import com.xlccc.pojo.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
class SpringbootMongodbApplicationTests {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Test
    public void test1 (){
        List<User> userList = mongoTemplate.findAll(User.class);
        if(userList != null && userList.size() > 0 ){
            userList.forEach(user -> {
                System.out.println(user.toString());
            });
        }
    }

}
