package com.xlccc.producer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName KafkaProducer
 * @Description TODO
 * @Author Linkp
 * @Date 2020/1/14 12:51 AM
 **/
@Component
public class KafkaProducer {
    /**
     * 订阅指定主题的消息
     * @param record 消息记录
     */
    @KafkaListener(topics = {"sunday"})
    public void listen(ConsumerRecord record){
        System.out.println(record.topic()+":"+record.value());
    }
}
