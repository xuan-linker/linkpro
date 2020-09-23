# springboot-zookeeper

> 此 demo 主要演示了 Spring Boot 简单使用 Zookeeper

### Docker安装Zookeeper

1. docker hub for zookeeper

`https://hub.docker.com/_/zookeeper`

2. 拉取镜像

`docker pull zookeeper`

3. 启动单机zookeeper

`docker run -p 2181:2181 --name link-zookeeper --restart always -d zookeeper`

-- restart always：始终重新启动zookeeper

### Mac安装Zookeeper

安装教程：http://xlccc.com/archives/linpro-springboot-zookeeper

### pom.xml

```xml
    <dependencies>
        <dependency>
            <groupId>org.apache.zookeeper</groupId>
            <artifactId>zookeeper</artifactId>
            <version>3.5.4-beta</version>
        </dependency>
    </dependencies>
```

### ZookeeperUtil

```java
import ZkProps;
import ZkWatcher;
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
        //zkClient.close();

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

        //第三个参数表示当前节点的数据版本，一般先获取数据stat，然后指定数据版本
        zkClient.setData("/link", "hello".getBytes(), stat.getVersion());

        zkClient.close();
    }

}

```
### ZkWatcher
```java
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

/**
 * @Author Linker
 * @Date 2020/3/21 3:17 下午
 * @Version 1.0
 */
public class ZkWatcher implements Watcher {
    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("ZkWatcher.process------" + watchedEvent);

    }
}

```

### ZkProps
##### 方法一
```java
/**
 * @Author Linker
 * @Date 2020/1/15 1:52 AM
 * @Version 1.0
 */
public class ZkProps {
    public String url = "127.0.0.1:2181";

    public int timeout = 2000;

    public int retry = 3;
}

```
##### 方法二
```yaml
zk:
  url: 127.0.0.1:2181
  timeout: 2000
  retry: 3
```

* [Apache Zookeeper](https://zookeeper.apache.org)
* [最简单的SpringBoot-ZooKeeper入门项目](http://xlccc.com/archives/linpro-springboot-zookeeper)

