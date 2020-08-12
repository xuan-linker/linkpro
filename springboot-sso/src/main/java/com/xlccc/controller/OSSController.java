package com.xlccc.controller;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import com.xlccc.User;
import com.xlccc.common.JsonResult;
import com.xlccc.common.JsonUtils;
import com.xlccc.service.IUserService;
import org.apache.tomcat.util.digester.Digester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author Linker
 * @date 2020/8/11 21:23
 * @description：
 */
@Controller
public class OSSController {

    @Autowired
    private IUserService userService;
    @Autowired
    private StringRedisTemplate redisTemplate;

    public static final String USER_TOKEN_KEY = "user_token_key";

    public static final long SESSION_EXPIRE_TIME = 3600l;

    public static final String REGISTER_USER_URL = "?";

    public static final String USER_LOGIN_URL = "?";

    @PostMapping("/login")
    public JsonResult login(String account, String password) {

        User user = userService.selectUser(account);


        if (user == null) {
            return new JsonResult(400, "用户不存在");
        }

        String md5Hex1 = DigestUtil.md5Hex(password);

        if (!md5Hex1.equals(user.getPassword())) {
            return new JsonResult(400, "密码错误");
        }

        //登录成功，把用户信息写入redis
        //生成一个token
        String token = UUID.randomUUID().toString().trim();
        redisTemplate.opsForValue().set(USER_TOKEN_KEY + ":" + token, JsonUtils.objectToJson(user));

        //设置session的过期时间
        // public void expire(String key, long timeout) {
        //        redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
        //    }
        redisTemplate.expire(USER_TOKEN_KEY + ":" + token, SESSION_EXPIRE_TIME, TimeUnit.SECONDS);

        return new JsonResult(token);
    }

    /**
     * 二次登录验证
     * 是否已经登录
     * 是则ok
     * 否则拒绝
     *
     * @param account
     * @param password
     * @param request
     * @param response
     * @return
     */
    public JsonResult login(String account, String password,
                            HttpServletRequest request, HttpServletResponse response) {
        //请求参数
//        Map<String, Object> param = new HashMap<>();
//        param.put("account", account);
//        param.put("password", password);
//        //登录处理
//        String stringResult = HttpClientUtil.doPost(REGISTER_USER_URL + USER_LOGIN_URL, param);
//        JsonResult result = JsonResult.format(stringResult);
//        //登录出错
//        if (result.getStatus() != 200) {
//            return result;
//        }
//
//        //登录成功后把取token信息，并写入cookie
//        String token = (String) result.getData();
//        //写入cookie
//        CookieUtils.setCookie(request, response, "TT_TOKEN", token);
//        //返回成功
//        return result;
        return new JsonResult();
    }
}
