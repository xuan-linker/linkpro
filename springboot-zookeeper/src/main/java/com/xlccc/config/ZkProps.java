package com.xlccc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author Linker
 * @Date 2020/1/15 1:52 AM
 * @Version 1.0
 */
@Component
public class ZkProps {
    public static String URL;

    public static int TIMEOUT;

    public static int RETRY;

    @Value("${zk.url}")
    public void setURL(String url) {
        ZkProps.URL = url;
    }

    @Value("${zk.timeout}")
    public void setTIMEOUT(int timeout) {
        ZkProps.TIMEOUT = timeout;
    }

    @Value("${zk.retry}")
    public void setRETRY(int retry) {
        ZkProps.RETRY = retry;
    }
}
