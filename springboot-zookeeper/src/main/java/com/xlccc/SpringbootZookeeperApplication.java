package com.xlccc;

import com.xlccc.Utils.ZookeeperUtil;
import org.apache.zookeeper.KeeperException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class SpringbootZookeeperApplication {

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ZookeeperUtil zu = new ZookeeperUtil();
        zu.ZkConnection();
        zu.getAll();
        zu.create();
        zu.getData();
        zu.update();
        zu.getAll();
//        SpringApplication.run(SpringbootZookeeperApplication.class, args);
    }

}
