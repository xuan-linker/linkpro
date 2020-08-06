package com.xlccc.service.impl;

import com.aliyun.openservices.log.Client;
import com.aliyun.openservices.log.common.LogItem;
import com.aliyun.openservices.log.exception.LogException;
import com.aliyun.openservices.log.request.ListLogStoresRequest;
import com.aliyun.openservices.log.request.PutLogsRequest;
import com.xlccc.service.LogServerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import static com.xlccc.common.LogServerConfig.getClient;

/**
 * @author Linker
 * @date 2020/8/6 16:45
 * @description：
 */
@Service
public class LogServerServiceImpl implements LogServerService {

    /**
     * 项目名称
     */
    private String project = "";
    /**
     * 日志库名称
     */
    private String logStore = "";

    private String topic = "";


    @Override
    public void test() throws LogException {
        Client client = getClient();
        //列出当前 project 下的所有日志库名称
        int offset = 0;
        int size = 100;

        String logStoreSubName = "";
        ListLogStoresRequest req1 = new ListLogStoresRequest(project, offset, size, logStoreSubName);
        ArrayList<String> logStores = client.ListLogStores(req1).GetLogStores();

        System.out.println("ListLogs:" + logStores.toString() + "\n");
        //写入日志
        String source = "";

        //连续发送10个数据包，每个数据包有10条日志
        for (int i = 0; i < 10; i++) {
            Vector<LogItem> logGroup = new Vector<LogItem>();
            for (int j = 0; j < 10; j++) {
                LogItem logItem = new LogItem((int) (new Date().getTime() / 1000));
                logItem.PushBack("index" + String.valueOf(j), "my log: " + String.valueOf(i * 10 + j));
                logGroup.add(logItem);
            }
            PutLogsRequest req2 = new PutLogsRequest(project, logStore, topic, source, logGroup);

            client.PutLogs(req2);


        }

    }
}
