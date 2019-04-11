package com.spring.cloud.fegin.hello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Stsy
 * @Date: 2018/12/4 17:08
 */
@RestController
@Configuration
public class ConsumerController {

    @Autowired
    InfoClient infoClient;

    @RequestMapping(value = "/consumerInfo", method = RequestMethod.GET)
    public String consumerInfo(){
        return infoClient.info();
    }
}
