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
        JSONObject jsonObject = new JSONObject(str);

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