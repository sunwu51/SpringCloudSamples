# Spring Cloud Config
## Config Server
## 添加依赖
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-config-server</artifactId>
</dependency>
```
## 配置文件 
```
server.port=7105
spring.application.name=config-server

spring.cloud.config.server.git.uri=https://github.com/sunwu51/SpringConfigFile
spring.cloud.config.server.git.username=xxx
spring.cloud.config.server.git.password=xxx
```
配置文件主要是添加git服务器的仓库地址

## 注入方式
在springboot主类上添加注解即可，如此就能可以从git获取配置文件内容了
```
@EnableConfigServer
```
## 配置规范
- 1 git仓库需要将所有配置文件放在根目录

- 2 每个文件命名为`{appname}-{profile}.{yml/properties}`  
    例如`app-p1.yml`如果有多个`-`：`app-p-dev.yml`则代表profile为`p-dev`

- 3 git的分支代表`label`

- 4 获取配置信息的url：`[/{label}]/{appname}/{profile}`label缺省则为master

## 小结
引入依赖，添加config-server配置，启动类加`@EnableConfigServer`。完成服务端配置。