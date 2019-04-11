package com.spring.cloud.eureka.client.first.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Stsy
 * @Date: 2018/10/9 9:15
 */
@RestController
public class HiController {

    @Value("${server.port}")
    String port;

    @GetMapping("/hi")
    public String Home(@RequestParam String name){
        return "hi "+name+",i am from port:"+port;
    }
}
