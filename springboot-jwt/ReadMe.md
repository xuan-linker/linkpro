# springboot-jwt

> 此 demo 主要演示了 Spring Boot 简单使用 Jwt

### pom.xml
```xml
<!-- jwt -->
<dependency>
   <groupId>com.auth0</groupId>
   <artifactId>java-jwt</artifactId>
   <version>3.8.2</version>
</dependency>
```
### JwtUtil
```java
package com.xlccc.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.xlccc.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Linker
 * @Date 2020/3/25 9:26 上午
 * @Version 1.0
 * @Todo: Jwt工具类，生成JWT和认证
 */
public class JwtUtil {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);
    /**
     * 密钥
     */
    private static final String SECRET = "xlccc.com";

    /**
     * 过期时间(s)
     **/
    private static final long EXPIRATION = 1800L;

    /**
     * 生成用户token,设置token超时时间
     */
    public static String createToken(User user) {
        //过期时间
        Date expireDate = new Date(System.currentTimeMillis() + EXPIRATION * 1000);
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        String token = JWT.create()
                // 添加头部
                .withHeader(map)
                //可以将基本信息放到claims中
                .withClaim("id", user.getId())
                .withClaim("userName", user.getUserName())
                .withClaim("name", user.getName())
                //超时设置,设置过期的日期
                .withExpiresAt(expireDate)
                //签发时间
                .withIssuedAt(new Date())
                //SECRET加密
                .sign(Algorithm.HMAC256(SECRET));
        return token;
    }

    /**
     * 校验token并解析token
     */
    public static Map<String, Claim> verifyToken(String token) {
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            jwt = verifier.verify(token);
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error("token解码异常");
            //解码异常则抛出异常
            return null;
        }
        return jwt.getClaims();
    }

}
```
### Filter过滤器
```java
package com.xlccc.filter;

import com.auth0.jwt.interfaces.Claim;
import com.xlccc.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @Author Linker
 * @Date 2020/3/24 7:08 下午
 * @Version 1.0
 * Todo: Jwt过滤器，拦截 /user请求
 */

@Slf4j
@WebFilter(filterName = "JwtFilter", urlPatterns = "/user/*")
public class JwtFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("Filter for Jwt 初始化...");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;

        response.setCharacterEncoding("UTF-8");

        //获取 Header中的Token
        final String token = request.getHeader("Authorization");

        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            chain.doFilter(request, response);
        } else {
            if (token == null) {
                response.getWriter().write("没有token！");
                return;
            }
            Map<String, Claim> userData = JwtUtil.verifyToken(token);
            if (userData == null) {
                response.getWriter().write("token不合法");
                return;
            }
            Integer id = userData.get("id").asInt();
            String name = userData.get("name").asString();
            String userName = userData.get("userName").asString();

            //拦截器 拿到用户信息 放到request中
            request.setAttribute("id", id);
            request.setAttribute("name", name);
            request.setAttribute("userName", userName);
            chain.doFilter(req, res);
        }
    }
    @Override
    public void destroy() {
        log.info("Filter for Jwt 销毁...");
    }
}
```
### UserController
模拟登录
```java
package com.xlccc.controller;

import com.xlccc.utils.JwtUtil;
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

```
### User
```java
package com.xlccc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author Linker
 * @Date 2020/3/25 9:26 上午
 * @Version 1.0
 */
@Data
@AllArgsConstructor
public class User {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String userName;
    private String name;
    private String password;
}

```
### SpringbootJwtApplication
启动类，加载Filter过滤器
```java
package com.xlccc;

import com.xlccc.filter.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJwtApplication.class, args);
    }
    /**
     * 注册filter
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean  bean = new FilterRegistrationBean(new JwtFilter());
        bean.addUrlPatterns("/user/*");
        return bean;
    }
}
```
* [最简单的SpringBoot-Jwt入门项目](http://xlccc.com/archives/linker-springboot-jwt)
* [JWT官网](https://jwt.io/)
* [Springboot-整合filter的两种方式](https://blog.csdn.net/u010675669/article/details/91846236)
* [SpringBoot集成JWT实现权限认证](https://juejin.im/post/5dca491ff265da4d0e00afbf)



