# Spring Cloud Netflix
## Eureka Server
注意：server同时也是client 
## 配置文件 
单个服务器配置文件
```
server.port=8761
eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false
eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka/
```
两个false是作为客户端不注册到服务端，以及不从服务端获取注册信息。最后一项是配置Eu服务器url（此时自己作为客户端），配置的Server地址还是指向自己，不过设置了不向自己注册和获取信息了。
可选的信息
```
eureka.instance.hostname=eurekademo
eureka.instance.ip-address=127.0.0.1
eureka.instance.prefer-ip-address=true
```
设置自己这个实例的hostname和ip地址，因为默认会将hostname注册到服务器。  
prefer-ip-address则是用ip进行注册，而不是hostname。

## 注入方式
在springboot主类上添加注解即可，启动后8761端口就是服务地址。
```
@EnableEurekaServer
```