package com.oauth.function.websocket;

import com.oauth.function.CommonTests;

import java.util.HashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


public class WebsocketTests extends CommonTests {

    private final static String websocket_url = "/messageCenter";

    public void websocketTest() throws Exception {

        String accessToken = obtainAccessToken();
        System.err.println("access_token=" + accessToken);

        HashMap<String, Object> map = new HashMap<>();
        map.put("param","666");

        execute(post(websocket_url+"/pushVideoListToWeb"), map, accessToken);

    }}