# springboot-security

> 此 demo 主要演示了 Spring Boot 简单使用 Security

### pom.xml
```xml
<dependencies>
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-security</artifactId>
   </dependency>

   <dependency>
       <groupId>org.springframework.security</groupId>
       <artifactId>spring-security-test</artifactId>
       <scope>test</scope>
   </dependency>
</dependencies>
```

### SpringSecurityConfig
```java
package com.xlccc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Author Linker
 * @Date 2020/3/26 10:17 下午
 * @Version 1.0
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 添加身份认证信息
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("123456")
                .roles("ADMIN")
                .and()
                .withUser("linker")
                .password("123456")
                .roles("USER")
                .and()
                .passwordEncoder(new CustomPasswordEncoder());
    }

    /**
     * 配置HTTP权限校验规则
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout().permitAll()
                .and()
                .formLogin();
        http.csrf().disable();
    }

    /**
     * 配置WEB资源校验规则
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**","/css/*","/images/**");

    }
}

```
### CustomPasswordEncoder
```java
package com.xlccc.config;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author Linker
 * @Date 2020/3/26 10:16 下午
 * @Version 1.0
 */
public class CustomPasswordEncoder implements PasswordEncoder {
    /**
     * 加密方法
     * @param charSequence
     * @return
     */
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    /**
     * 匹配方法
     * @param charSequence
     * @param s
     * @return
     */
    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(charSequence.toString());
    }
}
```
### HelloController
```java
package com.xlccc.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Linker
 * @Date 2020/3/26 10:13 下午
 * @Version 1.0
 */
@RestController
public class HelloController {
    /**
     * 所有用户都可以访问
     * @return
     */
    @RequestMapping("/")
    public String Home() {
        return "Hello Home";
    }

    /**
     * ADMIN用户登录后可访问
     * @return
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/hello")
    public String Hello() {
        return "Hello World";
    }
}

```

### SpringbootSecurityApplication
```java
 import org.springframework.boot.SpringApplication;
 import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
 import org.springframework.boot.autoconfigure.SpringBootApplication;
 import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
 
 @SpringBootApplication
 @EnableAutoConfiguration
 @EnableGlobalMethodSecurity(prePostEnabled = true)
 public class SpringbootSecurityApplication {
 
     public static void main(String[] args) {
         SpringApplication.run(SpringbootSecurityApplication.class, args);
     }
 
 }

```
### 参考

* [最简单的Springboot-Security入门项目](http://xlccc.com/archives/linker-springboot-security)
* [SpringSecurity 官网](https://spring.io/projects/spring-security)
* [SpringBoot+Vue之SpringSecurity登录与授权](https://juejin.im/post/5ccb0acb6fb9a032435db68f)

