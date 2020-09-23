package com.xlccc.springbootzookeeper;

import com.xlccc.utils.ZookeeperUtil;
import org.apache.zookeeper.KeeperException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static com.xlccc.config.ZkProps.*;

@SpringBootTest
class SpringbootZookeeperApplicationTests {

    @Test
    void test1() {
        System.out.println(URL + TIMEOUT + RETRY);
    }

    @Test
    void testForZookeeper() throws InterruptedException, IOException, KeeperException {

        ZookeeperUtil zu = new ZookeeperUtil();
//        zu.zkConnection();
//        zu.delete();
        zu.getAll();
        zu.create();
        zu.getData();
        zu.update();
        zu.getAll();
        zu.delete();
    }

}
