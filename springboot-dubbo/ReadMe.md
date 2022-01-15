# Dubbo Quick Started

##### How to build Zookeeper

1. Download Zookeeper

`wget https://archive.apache.org/dist/zookeeper/zookeeper-3.4.12/zookeeper-3.4.12.tar.gz`

2. tar -zxvf  zookeeper-3.4.12.tar.gz

3. Rename

``` 
mv zookeeper-3.4.12 zookeeper
```

4. Enter zookeeper folder，build folder name of `data`

```
mkdir data
```

5. Enter /zookeeper/config, copy zoo_sample.cfg , rename zoo.cfg

```
cp zoo_sample.cfg zoo.cfg
```

`vim zoo.cfg `

```
dataDir=/usr/local/zookeeper/data
```

6. start for test

Enter /zookeeper/bin

```
 ./zkServer.sh start
```

`./zkServer.sh status` for zookeeper's status or `netstat -lntup` can find zookeeper had use port of 2181

start dubbo-provider
start dubbo-consumer

update program path , not single

### Reference Documentation
For further reference, please consider the following sections:

* [Apache Dubbo](http://dubbo.apache.org/)
* [使用SpringBoot+Dubbo搭建一个简单的分布式服务](https://www.javazhiyin.com/25919.html)

