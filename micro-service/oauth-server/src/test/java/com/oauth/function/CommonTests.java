package com.oauth.function;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.util.JacksonJsonParser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CommonTests {

    @Autowired
    protected PasswordEncoder encoder;

    @Autowired
    protected MockMvc mockMvc;

    //clientId
    protected final static String CLIENT_ID = "client";
    //clientSecret
    protected final static String CLIENT_SECRET = "secret";
    //用户名
    protected final static String USERNAME = "admin";
    //密码
    protected final static String PASSWORD = "admin";
    //scope
    protected final static String SCOPE = "all";

    protected final static String CONTENT_TYPE = "application/json;charset=UTF-8";

    protected void execute(MockHttpServletRequestBuilder requestBuilder, Object obj, String accessToken) throws Exception {
        MvcResult mvcResult = mockMvc.perform(requestBuilder
                .header("Authorization", "bearer " + accessToken)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSON.toJSONString(obj)))
                .andReturn();// 得到返回结果
        MockHttpServletResponse response = mvcResult.getResponse();
        System.err.println(response.getStatus());
        System.err.println(response.getContentAsString());
    }

    protected String obtainAccessToken() throws Exception {
        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("client_id", CLIENT_ID);
        params.add("client_secret", CLIENT_SECRET);
        params.add("username", USERNAME);
        params.add("password", PASSWORD);
        params.add("scope", SCOPE);

        // @formatter:off
        ResultActions result = mockMvc.perform(post("/oauth/token")
                .params(params)
                .accept(CONTENT_TYPE))
                .andExpect(status().isOk());

        // @formatter:on

        String resultString = result.andReturn().getResponse().getContentAsString();

        JacksonJsonParser jsonParser = new JacksonJsonParser();
//        System.out.println(jsonParser.parseMap(resultString).get("access_token").toString());
        return jsonParser.parseMap(resultString).get("access_token").toString();
    }

}
