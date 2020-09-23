# springboot-redis-first

> 此 demo 主要演示了 Spring Boot 简单使用 Redis
### Docker安装Redis

docker hub for redis
`https://hub.docker.com/_/redis`

启动redis 并设置密码
`docker run -p 6379:6379 --name redis -d redis  --requirepass "admin"`


### 安装Redis
下载地址:https://redis.io/download

Redis安装教程详见：http://xlccc.com/archives/java-redis-basics1

#### 设置redis密码
```
redis 127.0.0.1:6379> config set requirepass admin
```
### pom.xml
```xml
 <!--整合redis-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
    <!--springboot2.o默认使用的redis客户端是lettuce-->
    <exclusions>
        <exclusion>
            <groupId>io.lettuce</groupId>
            <artifactId>lettuce-core</artifactId>
        </exclusion>
    </exclusions>
</dependency>

 <!--jedis-->
<dependency>
    <groupId>redis.clients</groupId>
    <artifactId>jedis</artifactId>
</dependency>
```

### Product
```java
package com.xlccc.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author Linker
 * @Date 2020/3/21 10:49 下午
 * @Version 1.0
 */
@Data
@Setter
@Getter
public class Product {
    private Integer id ;
    private String name ;
    private Integer price ;
}

```

### JsonUtils
```java
package com.xlccc.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

/**
 * @Author Linker
 * @Date 2020/3/21 10:49 下午
 * @Version 1.0
 * @Todo Json与Object相互转换的工具类
 */
public class JsonUtils {

    /**
     * 定义Jackson对象
     */
    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 将对象转换为json字符串
     */
    public static String objectToJson(Object data) {
        try {
            String jsonString = objectMapper.writeValueAsString(data);
            return jsonString;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将json字符串转换为对象
     */
    public static <T> T jsonToObject(String jsonString, Class<T> clazz) {
        try {
            T t = objectMapper.readValue(jsonString, clazz);
            return t;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}


```
### RedisUtils
```java
package com.xlccc.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Author Linker
 * @Date 2020/3/21 10:49 下午
 * @Version 1.0
 * @Todo Redis工具类 将Spring提供的StringRedisTemplate封装成Redis常用命令
 */
@Component
public class RedisUtils {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /*
     *  操作String类型
     */

    /**
     * 设置key-value
     */
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 设置key-value和超时时间（秒）
     */
    public void set(String key, String value, long timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 返回key所关联的字符串值
     */
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }


    /*
     *  操作List类型
     */

    /**
     * 向列表key的头部添加一个value，返回执行后列表的长度
     */
    public long lpush(String key, String value) {
        return redisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * 向列表key的尾部添加一个value，返回执行后列表的长度
     */
    public long rpush(String key, String value) {
        return redisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * 弹出列表key的头部元素
     */
    public String lpop(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    /**
     * 获取List
     */
    public List<String> lrange(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }


    /*
     * 操作Hash类型
     */

    /**
     * 向哈希表key中添加一个field，值为value
     */
    public void hset(String key, String field, Object value) {
        redisTemplate.opsForHash().put(key, field, value);
    }

    /**
     * 返回哈希表key中给定field的值
     */
    public String hget(String key, String field) {
        return (String) redisTemplate.opsForHash().get(key, field);
    }

    /**
     * 返回哈希表key中所有的域和值
     */
    public Map<Object, Object> hgetall(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 删除哈希表key中的一个或多个指定域
     */
    public void hdel(String key, Object... fields) {
        redisTemplate.opsForHash().delete(key, fields);
    }

    /*
     * 其他操作
     */

    /**
     * 查找所有符合给定模式pattern的key
     */
    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    /**
     * 设置给定key的过期时间，单位秒
     */
    public void expire(String key, long timeout) {
        redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 返回给定key的剩余过期时间，以秒为单位
     */
    public long ttl(String key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * 删除key
     */
    public void del(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 增加key
     */
    public long incr(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }
}
```
### application.properties
```properties
# Redis配置
spring.redis.host=127.0.0.1
spring.redis.port=6379
spring.redis.password=admin
spring.redis.database=0
spring.redis.jedis.pool.max-active=100
spring.redis.jedis.pool.max-idle=10
spring.redis.jedis.pool.min-idle=3
```
### 测试运行
```java
package com.xlccc.springbootredisfirst;

import com.xlccc.util.RedisUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
class FirstRedisApplicationTests {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	private RedisUtils redisUtils;

	@Test
	public void contextLoads() {
	}

	/**
	 * 使用stringRedisTemplate
	 * Redis数据类型：String、List、Set、ZSet、Hash
	 */
	@Test
	public void test1(){
//		 ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
//		 ListOperations<String, String> stringStringListOperations = stringRedisTemplate.opsForList();
//		 SetOperations<String, String> stringStringSetOperations = stringRedisTemplate.opsForSet();
//		 ZSetOperations<String, String> stringStringZSetOperations = stringRedisTemplate.opsForZSet();
//		 HashOperations<String, Object, Object> stringObjectObjectHashOperations = stringRedisTemplate.opsForHash();

		/*
		 * 操作String
		 */
//		 stringRedisTemplate.opsForValue().set("username","admin");
//		 System.out.println(stringRedisTemplate.opsForValue().get("username"));

		/*
		 * 操作List
		 */
//		 stringRedisTemplate.opsForList().leftPush("names","tom");
//		 stringRedisTemplate.opsForList().leftPushAll("names","aaa","bbb","ccc");
//		 System.out.println(stringRedisTemplate.opsForList().range("names",0,-1));

		/*
		 * 存储对象
		 */
//		Product product = new Product();
//		product.setId(1);
//		product.setName("曲奇");
//		product.setPrice(100);

		//将对象转换为json字符串
//		 String jsonString = JsonUtils.objectToJson(product);
//		 System.out.println(jsonString);
//		 stringRedisTemplate.opsForValue().set("user",jsonString);

		//将json字符串转换为对象
//		String str = stringRedisTemplate.opsForValue().get("user");
//		User u = JsonUtils.jsonToObject(str, User.class);
//		System.out.println(u);
	}

	/**
	 * 使用redisTemplate
	 */
	@Test
	public void test2(){
		redisTemplate.opsForValue().set("sex","male");
		Object sex = redisTemplate.opsForValue().get("sex");
		System.out.println(sex);
	}

	/**
	 * 使用工具类RedisUtils
	 */
	@Test
	public void test3(){
		redisUtils.set("age","21",20);
		System.out.println(redisUtils.get("age"));
	}
	@Test
	public void test4(){
		redisUtils.set("hello","world");
		System.out.println(redisUtils.get("hello"));
	}
}

```
### 参考
* [Redis官网](https://redis.io/)
* [最简单的SpringBoot-Redis入门项目](http://xlccc.com/archives/linpro-springboot-redis)

