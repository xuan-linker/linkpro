package com.xlccc.Utils;

import com.xlccc.config.props.ZkProps;
import com.xlccc.config.props.ZkWatcher;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;

/**
 * @Author Linker
 * @Date 2020/1/15 1:54 AM
 * @Version 1.0
 */
public class ZookeeperUtil {

    public void ZkConnection() throws IOException, InterruptedException {
        ZkProps zkProps = new ZkProps();
        ZooKeeper zkClient = new ZooKeeper(zkProps.url, zkProps.timeout, new ZkWatcher());

        Thread.sleep(2000);
        System.out.println(zkClient.getState());
//        zkClient.close();

    }

    public void getAll() throws IOException, KeeperException, InterruptedException {
        ZkProps zkProps = new ZkProps();
        ZooKeeper zkClient = new ZooKeeper(zkProps.url, zkProps.timeout, new ZkWatcher());
//第二个参数表示是否监视该节点
        List<String> children = zkClient.getChildren("/", true);
        System.out.println(children);

        zkClient.close();

    }

    public void create() throws IOException, KeeperException, InterruptedException {
        ZkProps zkProps = new ZkProps();
        ZooKeeper zkClient = new ZooKeeper(zkProps.url, zkProps.timeout, new ZkWatcher());

        zkClient.create("/link", "pro".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        zkClient.close();


    }

    public void getData() throws IOException, KeeperException, InterruptedException {
        ZkProps zkProps = new ZkProps();
        ZooKeeper zkClient = new ZooKeeper(zkProps.url, zkProps.timeout, new ZkWatcher());

        byte[] data = zkClient.getData("/link", true, null);
        System.out.println(new String(data));
        zkClient.close();

    }

    public void delete() throws IOException, KeeperException, InterruptedException {
        ZkProps zkProps = new ZkProps();
        ZooKeeper zkClient = new ZooKeeper(zkProps.url, zkProps.timeout, new ZkWatcher());

        zkClient.delete("/link", -1);

        zkClient.close();

    }

    public void update() throws IOException, KeeperException, InterruptedException {
        ZkProps zkProps = new ZkProps();
        ZooKeeper zkClient = new ZooKeeper(zkProps.url, zkProps.timeout, new ZkWatcher());
        Stat stat = new Stat();

        zkClient.setData("/link", "hello".getBytes(), stat.getVersion()); //第三个参数表示当前节点的数据版本，一般先获取数据stat，然后指定数据版本

        zkClient.close();
    }

}
