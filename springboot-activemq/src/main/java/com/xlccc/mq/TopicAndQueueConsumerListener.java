package com.xlccc.mq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * @author Linker
 * @date 1/14/2022 7:45 PM
 * @description：
 */
@Component
public class TopicAndQueueConsumerListener {
    /**
     * queue模式的消费者
     * SendTo("active.queue.result") 会将此方法返回的数据, 写入到 active.queue.result 中去.
     */
    @SendTo("active.queue.result")
    @JmsListener(destination="${spring.activemq.queue-name}", containerFactory="queueListener")
    public String readP2PQActiveueue(String message) {
        System.out.println("queue接受到：" + message);
        return message;
    }

    /**
     * queue模式的消费者
     */
    @JmsListener(destination="active.queue.result", containerFactory="queueListener")
    public void readP2PActiveQueue1(String message) {
        System.out.println("active.queue.result接受到：" + message);
    }

    /**
     * topic模式的消费者
     */
    @JmsListener(destination="${spring.activemq.topic-name}", containerFactory="topicListener")
    public void readTopicActiveQueue(String message) {
        System.out.println("topic接受到：" + message);
    }
}