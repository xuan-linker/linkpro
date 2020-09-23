package com.xlccc.utils;

import com.xlccc.config.ZkWatcher;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;

import static com.xlccc.config.ZkProps.TIMEOUT;
import static com.xlccc.config.ZkProps.URL;

/**
 * @Author Linker
 * @Date 2020/1/15 1:54 AM
 * @Version 1.0
 */
@Slf4j
public class ZookeeperUtil {

    public static ZooKeeper zkClient;

    public ZookeeperUtil() {
        this.zkClient = zkConnection();
    }

    public static ZooKeeper zkConnection() {
        try {
            ZooKeeper zkClient = new ZooKeeper(URL, TIMEOUT, new ZkWatcher());

            Thread.sleep(2000);
            log.info("ZkConnection() -> {}", zkClient.getState().toString());
            return zkClient;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void getAll() throws IOException, KeeperException, InterruptedException {
        //第二个参数表示是否监视该节点
        List<String> children = zkClient.getChildren("/", true);
        log.info("getAll() -> {}", children);
    }

    public void create() throws IOException, KeeperException, InterruptedException {
        zkClient.create("/link", "pro".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    public void getData() throws IOException, KeeperException, InterruptedException {
        byte[] data = zkClient.getData("/link", true, null);
        log.info("getData() -> {}", data);
    }

    public void delete() throws IOException, KeeperException, InterruptedException {
        zkClient.delete("/link", -1);
    }

    public void update() throws IOException, KeeperException, InterruptedException {
        Stat stat = new Stat();
        //第三个参数表示当前节点的数据版本，一般先获取数据stat，然后指定数据版本
        zkClient.setData("/link", "hello".getBytes(), stat.getVersion());
    }

}
