package com.xlccc.controller;

import com.xlccc.service.ShiroSampleService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Linker
 * @Date 2020/3/21 3:53 下午
 * @Version 1.0
 */
@RestController
public class ShiroSampleController {
    @Autowired
    private ShiroSampleService shiroSampleService;

    /**
     * 在登录之前，访问 /read 和 /write 接口都无效
     * http://localhost:8080/login?username=zhangsan&password=zhangsan
     * /read 和 /write 都可以访问
     * http://localhost:8080/login?username=lisi&password=lisi
     * 可以访问 /read，不能访问 /write
     *
     * 登出后，访问 /read 和 /write 接口都无效
     * @param username
     * @param password
     */
    @GetMapping("/login")
    public void login(String username, String password) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.setRememberMe(true);
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.login(token);
    }

    /**
     * http://localhost:8080/logout
     */
    @GetMapping("/logout")
    public void logout() {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
    }

    /**
     * http://localhost:8080/read
     * @return
     */
    @GetMapping("/read")
    public String read() {
        return this.shiroSampleService.read();
    }

    /**
     * http://localhost:8080/write
     * @return
     */
    @GetMapping("/write")
    public String write() {
        return this.shiroSampleService.write();
    }
}
