# springboot-wechat-login

> 此 demo 主要演示了 Spring Boot 简单获取小程序对应的用户openID


### WeChatUtil
```java
package com.xlccc.utils;

import lombok.extern.slf4j.Slf4j;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

/**
 * @Author Linker
 * @Date 2020/3/25 7:03 下午
 * @Version 1.0
 * @Todo:微信工具类
 */
@Slf4j
public class WeChatUtil {

    public static String httpRequest(String requestUrl, String requestMethod, String output) {
        try {
            URL url = new URL(requestUrl);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            if (null != output) {
                OutputStream outputStream = connection.getOutputStream();
                outputStream.write(output.getBytes("utf-8"));
                outputStream.close();
            }
            // 从输入流读取返回内容
            InputStream inputStream = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            connection.disconnect();
            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("请求失败");
        }
        return "";
    }
}
```
### User
Model
```java
package com.xlccc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Linker
 * @Date 2020/3/25 7:57 下午
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String weChatCode;

}
```
### UserController
```java
package com.xlccc.controller;

import com.xlccc.pojo.User;
import com.xlccc.utils.WeChatUtil;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Linker
 * @Date 2020/3/25 7:02 下午
 * @Version 1.0
 * @Todo:一个简单的小程序获取用户openID的方法
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    /**
     * 获取openID
     *
     * @param user
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/login")
    public String login(@RequestBody User user) {

        //1.根据用户密钥 获得openid(获取后输入进wechat_user中)
        // 微信小程序ID
        String appid = "微信小程序ID";
        // 微信小程序秘钥
        String secret = "微信小程序秘钥";
        // 根据小程序传过来的code向这个url发送请求
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appid + "&secret=" + secret + "&js_code=" + user.getWeChatCode() + "&grant_type=authorization_code";
        // 发送请求，返回Json字符串
        String str = WeChatUtil.httpRequest(url, "GET", null);

        // 转成Json对象 获取openid
        Map<String, Object> map = new HashMap<>();
        JSONObject jsonObject = new JSONObject(str);
        map.put("str", str);

        // 我们需要的openid，在一个小程序中，openid是唯一的
        String openid = null;
        try {
            openid = jsonObject.get("openid").toString();
        } catch (JSONException e) {
            e.printStackTrace();
            log.error("登录失败");
        }

        if (openid != null) {
            return openid;
        } else {
            return "fail";
        }
    }
}
```
### 参考
* [最简单的Springboot-Wechat-Login入门项目](http://xlccc.com/archives/linker-springboot-wechat-login)
* [微信官方文档](https://developers.weixin.qq.com/doc/)
* [小程序登录、微信网页授权（Java版）](https://segmentfault.com/a/1190000017442261)

