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
