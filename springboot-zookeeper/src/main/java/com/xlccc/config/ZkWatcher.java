package com.xlccc.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

/**
 * @Author Linker
 * @Date 2020/3/21 3:17 下午
 * @Version 1.0
 */
@Slf4j
public class ZkWatcher implements Watcher {
    @Override
    public void process(WatchedEvent watchedEvent) {
        log.info("ZkWatcher() -> {} ", "ZkWatcher.process------");
    }
}
