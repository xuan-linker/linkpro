package com.xlccc.demo;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author Linker
 * @date 1/14/2022 8:24 PM
 * @description：发布订阅模式消费者
 */
public class TopicConsumer {
    public static void main(String[] args) throws JMSException {
        // 创建连接工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        // 创建连接
        Connection connection = activeMQConnectionFactory.createConnection();
        // 打开连接
        connection.start();
        // 创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 创建队列目标,并标识队列名称，消费者根据队列名称接收数据
        Destination destination = session.createTopic("test-topic");
        // 创建消费者
        MessageConsumer consumer = session.createConsumer(destination);
        // 创建消费的监听
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println("消费的消息：" + textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
