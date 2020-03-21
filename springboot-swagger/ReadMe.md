# springboot-swagger

> 此 demo 主要演示了 Spring Boot 简单使用 Swagger


### pom.xml
```xml
        <dependency>
            <groupId>com.spring4all</groupId>
            <artifactId>swagger-spring-boot-starter</artifactId>
            <version>1.9.0.RELEASE</version>
        </dependency>
```

### UserController
```java
package com.xlccc.controller;

import com.xlccc.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @Author Linker
 * @Date 2020/3/17 10:49 下午
 * @Version 1.0
 */
@Api
@RestController
@RequestMapping(value = "/users")
public class UserController {

    static Map<Long, User> users = Collections.synchronizedMap(new HashMap<>());

    @GetMapping("/")
    @ApiOperation(value = "获取用户列表")
    public List<User> getUser() {
        List<User> r = new ArrayList<>(users.values());
        return r;
    }

    @PostMapping("/")
    @ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
    public String postUser(@RequestBody User user) {
        users.put(user.getId(), user);
        return "success";
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
    public User getUser(@PathVariable Long id) {
        return users.get(id);
    }

    @PutMapping("/{id}")
    @ApiImplicitParam(paramType = "path", dataType = "Long", name = "id", value = "用户编号", required = true, example = "1")
    @ApiOperation(value = "更新用户详细信息", notes = "根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    public String putUser(@PathVariable Long id, @RequestBody User user) {
        User u = users.get(id);
        u.setName(user.getName());

        users.put(id, u);
        return "success";
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除用户", notes = "根据url的id来指定删除对象")
    public String deleteUser(@PathVariable Long id) {
        users.remove(id);
        return "success";
    }

}


```
### User
```java
package com.xlccc.pojo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author Linker
 * @Date 2020/3/17 10:49 下午
 * @Version 1.0
 */
@Data
@Getter
@Setter
@ApiModel(description = "用户实体")
public class User {
    private Long Id ;
    private String name ;
}

```

### application.properties
```properties
swagger.title=spring-boot-starter-swagger
swagger.description=Starter for swagger 2.x
swagger.version=1.4.0.RELEASE
swagger.license=Apache License, Version 2.0
swagger.licenseUrl=https://www.apache.org/licenses/LICENSE-2.0.html
swagger.termsOfServiceUrl=https://github.com/xuan-linker/linkpro
swagger.contact.name=Linker
swagger.contact.url=http://www.xlccc.com
swagger.contact.email=xuanlccc@gmail.com
swagger.base-package=com.xlccc
swagger.base-path=/**

```
### 测试运行
启动项目
进入 `http://localhost:8080/swagger-ui.html`
### 参考
* [Swagger官网](https://swagger.io/)
* [最简单的SpringBoot-ZooKeeper入门项目](https://github.com/xuan-linker/linkpro/tree/master/springboot-swagger)

