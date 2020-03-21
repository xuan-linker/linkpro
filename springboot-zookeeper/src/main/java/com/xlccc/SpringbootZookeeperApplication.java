package com.xlccc;

import com.xlccc.Utils.ZookeeperUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class SpringbootZookeeperApplication {

    public static void main(String[] args) throws IOException, InterruptedException {
        ZookeeperUtil zookeeperUtil = new ZookeeperUtil();

        zookeeperUtil.ZkConnection();
//        SpringApplication.run(SpringbootZookeeperApplication.class, args);
    }

}
