package top.microfrank.springcloudeurekaclient;


import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/")
    public String index(){
        System.out.println("get /");
        return "Hello! I am Eureka-Client-Ribbon";
    }
    @GetMapping("/ribbon")
    public String test(){
        String str = restTemplate.getForObject(
                "http://eureka-client/",String.class);
        return "data from http://eureka-client/ is <<< "+str+" >>>";
    }
}
