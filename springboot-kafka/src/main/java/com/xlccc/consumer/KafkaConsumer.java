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
     * @param topic 主题，如果主题不存在，会自动创建主题
     * @param message　消息
     * @return
     */
    @RequestMapping("/sendMsg")
    public String sendMsg(String topic , String message){
        template.send(topic,message);
        return "success";
    }
}
