package com.xlccc.rocketmq.jms;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.stereotype.Component;

/**
 * @author Linker
 * @date 2020/11/23 10:08
 * @description：
 */
@Slf4j
@Component
public class Producer {

    private String producerGroup = "test_producer";

    private DefaultMQProducer producer;

    public Producer() {
        //示例生产者
        producer = new DefaultMQProducer(producerGroup);
        //不开启vip通道 开通端口会减2
        producer.setVipChannelEnabled(false);
        //绑定name server
        log.info("nameServer : " + JmsConfig.NAME_SERVER);
        producer.setNamesrvAddr(JmsConfig.NAME_SERVER);
        start();
    }

    /**
     * 对象在使用之前必须要调用一次，只能初始化一次
     */
    public void start() {
        try {
            this.producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    public DefaultMQProducer getProducer() {
        return this.producer;
    }

    /**
     * 一般在应用上下文，使用上下文监听器，进行关闭
     */
    public void shutdown() {
        this.producer.shutdown();
    }
}
