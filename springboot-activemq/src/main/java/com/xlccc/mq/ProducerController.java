package com.xlccc.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Destination;
import javax.jms.Queue;
import javax.jms.Topic;

/**
 * @author Linker
 * @date 1/14/2022 7:45 PM
 * @description：
 */
@RestController
public class ProducerController {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    @Autowired
    private Topic topic;

    /**
     * 点对点模式
     */
    @PostMapping("/queue/test")
    public String sendQueue(@RequestBody String str) {
        this.sendMessage(queue, str);
        return "success";
    }

    /**
     * 发布/订阅模式
     */
    @PostMapping("/topic/test")
    public String sendTopic(@RequestBody String str) {
        this.sendMessage(topic, str);
        return "success";
    }

    /**
     * 发送消息，destination是发送到的队列，message是待发送的消息
     */
    private void sendMessage(Destination destination, final String message) {
        jmsMessagingTemplate.convertAndSend(destination, message);
        // 将消息封装并发送到点对点模式队列：active.test
        //jmsMessagingTemplate.convertAndSend("active.test", message);
    }
}