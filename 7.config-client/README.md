# Spring Cloud Config
## Config Client
## 添加依赖
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-config</artifactId>
</dependency>
```
## 配置文件 
！！注意热部署的配置不能写在application.properties中，需要写在
```
bootstrap.properties
```
(该文件的优先级高于application)

```
# config server
spring.cloud.config.uri=http://localhost:7105

# 对应[appname]
spring.application.name=application

# 对应[profile]
spring.cloud.config.profile=profile1

# 对应[label]
spring.cloud.config.label=master
```
注意这里指定了config-server的uri，是不太好的。可以利用Eureka的自发现。
需要将config和server全都配置为Eureka-client，[参考](../2.eureka-client/README.md)
然后这里将配置`spring.cloud.config.uri`改为
```properties
spring.cloud.config.discovery.enabled=true

# 这个就是configserver的spring.application.name
spring.cloud.config.discovery.service-id=xxxx
```
## 注入方式
在配置更新后，需要重新加载的`Bean类`或者注入Bean的地方（如`Controller`上）添加注解
```
@RefreshScope
```
## 更新配置
需要用到`actuator`，直接POST config-client `/refresh`即可重新加载配置，自动配置参考Bus代码

## 小结
引入依赖，添加`bootstrap.properties`，合适的地方添加`@RefreshScope`。三步完成客户端的配置。

