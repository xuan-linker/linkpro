# springboot-docker

> 此 demo 主要演示了 CenterOS 简单部署 Springboot 项目

### pom.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.2.5.RELEASE</version>
        <relativePath/>
    </parent>
    <groupId>com.xlccc</groupId>
    <artifactId>springboot-docker</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>springboot-docker</name>
    <description>Demo project for Spring Boot</description>

    <!--Docker镜像名称-->
    <properties>
        <docker.image.prefix>springboot</docker.image.prefix>
    </properties>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
            <!-- Docker maven plugin -->
            <plugin>
                <groupId>com.spotify</groupId>
                <artifactId>docker-maven-plugin</artifactId>
                <version>1.0.0</version>
                <configuration>
                    <imageName>${docker.image.prefix}/${project.artifactId}</imageName>
                    <dockerDirectory>src/main/docker</dockerDirectory>
                    <resources>
                        <resource>
                            <targetPath>/</targetPath>
                            <directory>${project.build.directory}</directory>
                            <include>${project.build.finalName}.jar</include>
                        </resource>
                    </resources>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>

```
### HelloController
```java
package com.xlccc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName HelloController
 * @Description TODO
 * @Author Linker
 * @Date 2020/1/18 6:05 PM
 **/
@RestController
public class HelloController {
    @RequestMapping("/")
    public String index() {
        return "Hello World!--docker";
    }
}

```
### Spring Boot 项目添加 Docker 依赖

创建文件： main/docker/Dockerfile
```
FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD springboot-docker-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
```
* FROM ，表示使用 Jdk8 环境 为基础镜像，如果镜像不是本地的会从 DockerHub 进行下载
* VOLUME ，VOLUME 指向了一个/tmp的目录，由于 Spring Boot 使用内置的Tomcat容器，Tomcat 默认使用/tmp作为工作目录。这个命令的效果是：在宿主机的/var/lib/docker目录下创建一个临时文件并把它链接到容器中的/tmp目录
* ADD ，拷贝文件并且重命名
* ENTRYPOINT ，为了缩短 Tomcat 的启动时间，添加java.security.egd的系统属性指向/dev/urandom作为 ENTRYPOINT

### 测试运行

    http://localhost:8080/

### CenterOS 部署 docker 项目

CenterOS 安装docker

`yum install docker`

启动docker

`service docker start`

配置开机自动启动

`chkconfig docker on`

`systemctl start docker.service`

`systemctl enable docker.service`

配置Docker加速器

`vi  /etc/docker/daemon.json`

    #添加后：
    {
        "registry-mirrors": ["https://registry.docker-cn.com"],
        "live-restore": true
    }

也可选用网易的镜像地址：http://hub-mirror.c.163.com

	{
	    "registry-mirrors": ["http://hub-mirror.c.163.com"]
	}
	
重启生效

`systemctl restart docker`

验证是否安装成功

`docker version`

### 安装JDK

`yum -y install java-1.8.0-openjdk*`

    export JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.242.b08-0.el7_7.x86_64
    export PATH=$PATH:$JAVA_HOME/bin 

`source /etc/profile`

`java -version`

### 安装Maven环境

下载Maven安装包

`wget http://mirror.bit.edu.cn/apache/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz`

解压安装包

`tar -zxvf apache-maven-3.6.3-bin.tar.gz `

移动解压文件

`mv apache-maven-3.6.3 /usr/local/maven3`

配置全局变量

`/etc/profile`

     MAVEN_HOME=/usr/local/maven3
     export MAVEN_HOME
     export PATH=${PATH}:${MAVEN_HOME}/bin
 
生效文件

`source /etc/profile` 

测试安装成功

`mvn -version`   

### 上传项目至linux服务器

压缩springboot-docker工程

`tar czvf springboot-docker.tar.gz springboot-docker `

本地上传文件到服务器

`scp /Users/linker/github/springboot-docker.tar.gz root@IP:/root/`

解压

`tar zxvf springboot-docker.tar.gz `

进入项目目录
打包项目

`mvn package`

启动项目测试

`java -jar target/springboot-docker-0.0.1-SNAPSHOT.jar`

使用DockerFile创建镜像

`mvn package docker:build`

查看镜像

`docker images `

运行镜像

`docker run -p 8080:8080 -t springboot/springboot-docker`

查看正在运行的镜像

`docker ps `

通过浏览器访问项目

`http://IP:8080/`

结果
 
    Hello World!--docker
    
### 参考

* [Docker官网](https://www.docker.com/)
* [最简单的Docker 部署 Springboot 入门项目](http://xlccc.com/archives/linker-docker-springboot)

