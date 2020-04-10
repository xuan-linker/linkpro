package com.xlccc.core.request;

import com.xlccc.utils.OkHttpUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author Linker
 * @Date 2020/4/10 2:50 下午
 * @Version 1.0
 */
@SpringBootTest
public class OkHttpTests {

    String url = "http://www.baidu.com";
    OkHttpUtil okHttpUtil = new OkHttpUtil();

    @Test
    public void GetRequest() {
        okHttpUtil.getRequest(url);
    }

    @Test
    public void Head() {
        okHttpUtil.Head(url);
    }

    @Test
    public void PostString() {
        okHttpUtil.postString(url);
    }

    @Test
    public void PostBuffer() {
        okHttpUtil.postBuffer();
    }

    @Test
    public void AsyncGet() {
        okHttpUtil.asyncGet(url);
    }
}
