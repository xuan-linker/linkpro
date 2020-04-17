# springboot-springtask

> 此 demo 主要演示了 Spring Boot 简单使用SpringTask

### FirstSpringTask.class
```java
package com.xlccc.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author Linker
 * @Date 2020/4/17 10:14 上午
 * @Version 1.0
 * TODO:执行定时任务
 */
@Component
@Slf4j
public class FirstSpringTask {

    /**
     * @Scheduled(fixedRate = 1000)
     * @Scheduled(fixedRate = 500)
     */

    @Scheduled(cron = "10/5 * * * * ? ")
    private void HelloTask() {
        log.info("SpringTask -- Hello ");
    }

    @Async
    @Scheduled(fixedRate = 2000)
    public void AsyncTask() {
        log.info("AsyncTask -- Hello ");
    }

}

```

### SpringTaskConfig.class
```java

package com.xlccc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author Linker
 * @Date 2020/4/17 10:10 上午
 * @Version 1.0
 * TODO:定时任务配置
 */
@Configuration
@EnableScheduling
public class SpringTaskConfig {
}

```

### 参考

* [Spring @Scheduled定时任务的fixedRate,fixedDelay,cron的作用和不同](https://blog.csdn.net/m0_37479246/article/details/78970809)

