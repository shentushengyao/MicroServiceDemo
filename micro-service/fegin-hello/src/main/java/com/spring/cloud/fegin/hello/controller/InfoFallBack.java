package com.spring.cloud.fegin.hello.controller;

import org.springframework.stereotype.Component;

@Component
public class InfoFallBack implements InfoClient {
    @Override
    public String info() {
        return "fallback info";
    }
}
