# springboot-kafka

> 此 demo 主要演示了 SpringBoot 简单使用 Kafka

### pom.xml
```xml
<dependency>
    <groupId>org.springframework.kafka</groupId>
    <artifactId>spring-kafka</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.kafka</groupId>
    <artifactId>spring-kafka-test</artifactId>
    <scope>test</scope>
</dependency>
```
### KafkaConsumer
```java
package com.xlccc.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName KafkaConsumer
 * @Description TODO
 * @Author Linker
 * @Date 2020/1/14 12:51 AM
 **/
@RestController
public class KafkaConsumer {
    @Autowired
    private KafkaTemplate template;

    /**
     * 发送消息到Kafka
     *
     * @param topic   主题，如果主题不存在，会自动创建主题
     * @param message 　消息
     * @return
     */
    @RequestMapping("/sendMsg")
    public String sendMsg(String topic, String message) {
        template.send(topic, message);
        return "success";
    }
}

```
### KafkaProducer
```java
package com.xlccc.producer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName KafkaProducer
 * @Description TODO
 * @Author Linker
 * @Date 2020/1/14 12:51 AM
 **/
@Component
public class KafkaProducer {
    /**
     * 订阅指定主题的消息
     *
     * @param record 消息记录
     */
    @KafkaListener(topics = {"sunday"})
    public void listen(ConsumerRecord record) {
        System.out.println(record.topic() + ":" + record.value());
    }
}

```
### 参考
* [最简单的SpringBoot-Kafka入门项目](http://xlccc.com/archives/linkpro-springboot-kafka)
* [Kafka官网](https://kafka.apache.org/)
* [Mac下Kafka 安装与启动](https://www.jianshu.com/p/a581372f07b4)
