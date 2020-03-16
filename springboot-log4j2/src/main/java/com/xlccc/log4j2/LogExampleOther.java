package com.xlccc.log4j2;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogExampleOther {
    public static void main(String... args) {

        log.error("Something else is wrong here");
        log.trace("Spring boot启动初始化 trace");
        log.debug("Spring boot启动初始化 debug");
        log.info("Spring boot启动初始化 info");
        log.warn("Spring boot启动初始化 warn");

    }
}
