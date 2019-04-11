package com.spring.cloud.fegin.hello.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: Stsy
 * @Date: 2018/12/4 17:03
 */
@FeignClient(name = "cloud-provider", fallback = InfoFallBack.class, configuration = MyFeignConfig.class)
public interface InfoClient {

    //被请求微服务的地址
    @RequestMapping("/info")
    String info();
}