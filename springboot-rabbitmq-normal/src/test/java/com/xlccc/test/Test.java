package com.xlccc.test;

import com.xlccc.config.MsgProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Linker
 * @date 2020/9/28 16:27
 * @description：
 */
@SpringBootTest
public class Test {
    @Autowired
    private MsgProducer msgProducer;

    @org.junit.jupiter.api.Test
    public void test() {
        msgProducer.sendMsg("Link-mq-message");
    }
}
