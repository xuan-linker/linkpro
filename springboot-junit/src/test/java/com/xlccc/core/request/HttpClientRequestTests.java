package com.xlccc.core.request;

import com.xlccc.utils.HttpClientUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class HttpClientRequestTests {

    HttpClientUtil httpClientUtil = new HttpClientUtil();

    String url = "http://localhost:8080/";

    @Test
    void testForGet() {
        httpClientUtil.getRequest(url + "get");
    }

    @Test
    void TestForPost() {
        httpClientUtil.postRequest(url + "post");
    }

}
