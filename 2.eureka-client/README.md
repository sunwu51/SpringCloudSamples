# Spring Cloud Netflix
## Eureka Client
注意：server同时也是client 
## 配置文件 
客户端配置文件
```
server.port=7100
spring.application.name=eureka-client

eureka.instance.prefer-ip-address=true
eureka.client.service-url.defaultZone=http://localhost:8761/eureka/
```
上面配置的实例名称`eureka-client`会在eu服务器上注册，然后设置ip优先的原则是其他服务获取本服务的时候，按照ip地址去访问url，如果不设置则会按照hostname去获取。  
Eu服务器信息：
![image](img/1.jpg)

## 注入方式
在springboot主类上添加注解
```
@EnableEurekaClient
```
就可以在类中注入EurekaClient了
```java
@RestController
public class WebController {
    //这里IDEA报错，有俩bean不用管，IDEA搞错了
    @Autowired
    EurekaClient discoveryClient;

    //根据应用名获取一个实例
    @GetMapping("/instance")
    public InstanceInfo showInfo() {
        return discoveryClient.getNextServerFromEureka("eureka-client",false);
    }

    //主动向服务端注销自己这个实例
    @GetMapping("/shut")
    public String shut(){
        discoveryClient.shutdown();
        return "ok";
    }
}
```
InstanceInfo的内容为：
```json
{
    "instanceId": "localhost:eureka-client:7100",
    "app": "EUREKA-CLIENT",
    "appGroupName": null,
    "ipAddr": "192.168.4.1",
    "sid": "na",
    "homePageUrl": "http://192.168.4.1:7100/",
    "statusPageUrl": "http://192.168.4.1:7100/info",
    "healthCheckUrl": "http://192.168.4.1:7100/health",
    "secureHealthCheckUrl": null,
    "vipAddress": "eureka-client",
    "secureVipAddress": "eureka-client",
    "countryId": 1,
    "dataCenterInfo": {
        "@class": "com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo",
        "name": "MyOwn"
    },
    "hostName": "192.168.4.1",
    "status": "UP",
    "leaseInfo": {
        "renewalIntervalInSecs": 30,
        "durationInSecs": 90,
        "registrationTimestamp": 1515135792229,
        "lastRenewalTimestamp": 1515135792229,
        "evictionTimestamp": 0,
        "serviceUpTimestamp": 1515135791722
    },
    "isCoordinatingDiscoveryServer": false,
    "metadata": {
        "management.port": "7100"
    },
    "lastUpdatedTimestamp": 1515135792229,
    "lastDirtyTimestamp": 1515135791345,
    "actionType": "ADDED",
    "asgName": null,
    "overriddenStatus": "UNKNOWN"
}
```
## 安全问题
默认Eureka服务器是可以匿名访问的这样可能会有安全问题，最简单的保护措施是引入SpringSecurity并使用`HttpBasic`方式进行保护  

EurekaServer添加依赖与配置：
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```
```
security.user.name=admin
security.user.password=admin
security.basic.enabled=true
```
所有`eureka.client.service-url.defaultZone`修改为
```
eureka.client.service-url.defaultZone=http://admin:admin@localhost:8761/eureka/
```
