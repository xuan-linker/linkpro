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