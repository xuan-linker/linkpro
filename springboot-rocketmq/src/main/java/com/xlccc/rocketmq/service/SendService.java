package com.xlccc.rocketmq.service;

import com.xlccc.rocketmq.jms.Producer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Linker
 * @date 2020/11/23 10:06
 * @description：
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class SendService {
    private final Producer producer;

    /**
     * 1. 同步发送消息
     *
     * @param topic
     * @param content
     */
    public void sync(String topic, String content) {
        try {
            //创建消息
            Message message = new Message(topic, content.getBytes());
            //同步发送消息
            SendResult sendResult = producer.getProducer().send(message);
            log.info("Product-同步发送-Product信息={}", sendResult);
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * 2. 异步发送消息
     */
    public void async(String topic, String content) {
        //创建消息
        Message message = new Message(topic, content.getBytes());
        //异步发送消息
        try {
            producer.getProducer().send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    log.info("Product-异步发送-输出信息={}", sendResult);
                }

                @Override
                public void onException(Throwable e) {
                    e.printStackTrace();
                    //补偿机制，根据业务情况进行使用，看是否进行重试
                }
            });
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 3. 单项发送消息
     *
     * @param topic
     * @param content
     */
    public void oneWay(String topic, String content) {
        //创建消息
        Message message = new Message(topic, content.getBytes());
        //同步发送消息
        try {
            producer.getProducer().sendOneway(message);
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
