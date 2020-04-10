package com.xlccc.utils;

import okhttp3.*;
import okio.BufferedSink;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;


/**
 * @Author Linker
 * @Date 2020/4/10 2:49 下午
 * @Version 1.0
 */
public class OkHttpUtil {

    public void getRequest(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e + "请求异常");
        }
        if (!response.isSuccessful()) {
//            throw new IOException("服务器端错误:" + response);
            System.out.println("服务器端错误：" + response);
        }
        Headers responseHeaders = response.headers();
        for (int i = 0; i < responseHeaders.size(); i++) {
            System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
        }
        try {
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e + "响应体异常");
        }
    }

    public void Head(String url) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .header("User-Agent", "My super agent")
                .addHeader("Accept", "text/html")
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e + "请求异常");
        }
        if (!response.isSuccessful()) {
//            throw new IOException("服务器端错误: " + response);
            System.out.println("服务器端错误: " + response);
        }

        System.out.println(response.header("Server"));
        System.out.println(response.headers("Set-Cookie"));
    }

    public void postString(String url) {
        OkHttpClient client = new OkHttpClient();
        MediaType MEDIA_TYPE_TEXT = MediaType.parse("text/plain");
        String postBody = "Hello World";

        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(MEDIA_TYPE_TEXT, postBody))
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e + "服务器端错误");
        }
        if (!response.isSuccessful()) {
//            throw new IOException("服务器端错误: " + response);
            System.out.println("服务器端错误: " + response);
        }

        try {
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e + "响应体错误");
        }

    }

    public void postBuffer() {
        OkHttpClient client = new OkHttpClient();
        final MediaType MEDIA_TYPE_TEXT = MediaType.parse("text/plain");
        final String postBody = "Hello World";

        RequestBody requestBody = new RequestBody() {
            @Override
            public MediaType contentType() {
                return MEDIA_TYPE_TEXT;
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                sink.writeUtf8(postBody);
            }

            @Override
            public long contentLength() throws IOException {
                return postBody.length();
            }
        };

        Request request = new Request.Builder()
                .url("http://www.baidu.com")
                .post(requestBody)
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e + "服务器异常");
        }
        if (!response.isSuccessful()) {
//            throw new IOException("服务器端错误: " + response);
            System.out.println("服务器端错误: " + response);
        }


        try {
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e + "响应体异常");
        }
    }

//    public void CacheResponse() {
//         int cacheSize = 100 * 1024 * 1024;
//        File cacheDirectory = null;
//        try {
//            cacheDirectory = Files.createTempDirectory("cache").toFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Cache cache = new Cache(cacheDirectory,cacheSize);
//        OkHttpClient client = new OkHttpClient();
//        client.cache(new Cache(cacheDirectory,cacheSize),cacheSize)

//        OkHttpClient okHttpClient = new OkHttpClient();
//        OkHttpClient newClient = okHttpClient.newBuilder()
//                .cache(new Cache(mContext.getCacheDir(), 10240*1024))
//                .connectTimeout(20, TimeUnit.SECONDS)
//                .readTimeout(20, TimeUnit.SECONDS)

//    }


    public void asyncGet(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            public void onResponse(Response response) {
                if (!response.isSuccessful()) {
                    System.out.println("服务器端错误:" + response);
                }
                System.out.println(response.body().toString());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

            }
        });
    }
}
