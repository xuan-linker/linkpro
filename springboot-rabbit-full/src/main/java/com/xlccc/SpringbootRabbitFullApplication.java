package com.xlccc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Linker
 * @date 2021/2/3 15:45
 * @description：
 */
@SpringBootApplication
public class SpringbootRabbitFullApplication {
    /**
     * mall.order.direct（取消订单消息队列所绑定的交换机）:绑定的队列为mall.order.cancel，一旦有消息以
     * mall.order.cancel为路由键发过来，会发送到此队列。
     * <p>
     * mall.order.direct.ttl（订单延迟消息队列所绑定的交换机）:绑定的队列为mall.order.cancel.ttl，
     * 一旦有消息以mall.order.cancel.ttl为路由键发送过来，
     * 会转发到此队列，并在此队列保存一定时间，等到超时后会自动将消息发送到mall.order.cancel（取消订单消息消费队列）。
     */
    public static void main(String[] args) {
        SpringApplication.run(SpringbootRabbitFullApplication.class, args);
    }
}
