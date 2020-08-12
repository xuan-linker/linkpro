//package com.xlccc.common;
//
//import java.io.IOException;
//
///**
// * @author Linker
// * @date 2020/8/11 23:36
// * @description：
// */
//public class HttpClientUtil {
//    public void postRequest(String url) {
//        //创建HttpClient对象
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        //创建Http POST请求
//        HttpPost httpPost = new HttpPost(url);
//        //伪装浏览器请求
//        httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
//        CloseableHttpResponse response = null;
//        try {
//            //执行请求
//            response = httpClient.execute(httpPost);
//            //判断返回状态是否为200
//            if (response.getStatusLine().getStatusCode() == 200) {
//                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
//                System.out.println(content);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println(e + "执行异常");
//        } finally {
//            try {
//                if (response != null) {
//                    response.close();
//                }
//                httpClient.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//                System.out.println(e + "关闭异常");
//            }
//        }
//
//    }
//}
