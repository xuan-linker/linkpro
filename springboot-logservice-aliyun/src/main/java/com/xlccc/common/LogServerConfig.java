package com.xlccc.common;


import com.aliyun.openservices.log.Client;

/**
 * @author Linker
 * @date 2020/8/6 16:33
 * @description：
 */
public class LogServerConfig {
    public static String accessId = "";
    public static String accessKey = "";
    public static String host = "cn-hangzhou.log.aliyuncs.com";
//    Client client = new Client(host, accessId, accessKey);

    public static Client getClient() {
        return new Client(host, accessId, accessKey);
    }
}
