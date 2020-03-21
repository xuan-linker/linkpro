package com.xlccc.controller;

import com.xlccc.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Linker
 * @Date 2020/3/21 5:15 下午
 * @Version 1.0
 */
@Controller
public class UserController {

    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request, User inuser, String uname, String upwd) {
        System.out.println("用户名和密码是" + uname + upwd + " User-->" + inuser.toString());
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(uname, upwd);
        Subject subject = SecurityUtils.getSubject();
        try {
            //登录
            subject.login(usernamePasswordToken);
            User user = (User) subject.getPrincipal();
            request.getSession().setAttribute("user", user);
            return "index";
        } catch (AuthenticationException e) {
            return "login";
        }
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        // 执行注销
        if (subject.isAuthenticated()) {
            subject.logout();
        }
        request.getSession().removeAttribute("user");
        return "login";
    }

    @RequiresRoles("admin")
    @RequiresPermissions(value = "add")
    @RequestMapping(value = "/test")
    public String test(HttpServletRequest request, String test) {
        System.out.println(test);
        return "test";
    }

    @RequestMapping(value = "/{page}")
    public String show(@PathVariable("page") String page) {
        return page;
    }

    @ResponseBody
    @RequestMapping("/hello")
    public String hello() {
        return "Hello World";
    }
}

