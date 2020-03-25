package com.xlccc.controller;

import com.xlccc.Utils.JwtUtil;
import com.xlccc.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Linker
 * @Date 2020/3/24 7:41 下午
 * @Version 1.0
 * <p>
 * @Todo:用户模拟登录
 */
@Slf4j
@RestController
public class UserController {
    /**
     *   模拟数据库
     */
    static Map<Integer, User> userMap = new HashMap<Integer, User>() {{
        put(1, new User(
                1, "linker", "Linker", "admin"
        ));
        put(2, new User(
                2, "xlccc", "xLccc", "demo"
        ));
    }};

    /**
     * 用户登录
     * @param user
     * @return
     */
    @RequestMapping("/login")
    public String login(User user) {
        for (User u : userMap.values()
        ) {
            if (u.getUserName().equals(user.getUserName()) && u.getPassword().equals(u.getPassword())) {
                log.info("登录成功！生成token");
                String token = JwtUtil.createToken(u);
                return token;
            }
        }
        return null;
    }

    /**
     * 登录成功后，可以访问
     * @param request
     * @return
     */
    @RequestMapping("/user/getUserInfo")
    public String login(HttpServletRequest request) {

        Integer id = (Integer) request.getAttribute("id");
        String name = request.getAttribute("name").toString();
        String userName = request.getAttribute("userName").toString();

        return "UserInfo:Id" + id + ",Name=" + name + ",UserName=" + userName;
    }
}
