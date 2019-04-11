package com.example.eurekaclient1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @Author: Stsy
 * @Date: 2018/10/8 14:27
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/test")
    public String test(String test){
        return test+LocalDateTime.now().toString();
    }
}
