package com.example.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerFirstApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerFirstApplication.class, args);
    }
}
