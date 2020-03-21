package com.xlccc.config.props;

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
