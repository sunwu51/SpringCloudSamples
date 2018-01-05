package top.microfrank.springcloudeurekaclient;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Applications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PreDestroy;
import java.util.List;

/**
 * Created by Frank on 2018/1/5.
 */
@RestController
public class WebController {
    //这里IDEA报错，有俩bean不用管，IDEA搞错了
    @Autowired
    EurekaClient discoveryClient;

    RestTemplate restTemplate;

    @GetMapping("/")
    public String index(){
		System.out.println("get /");
        return "Hello! I am Eureka-Client";
    }
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
