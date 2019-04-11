package com.spring.boot.springbootservice.sys.security.component;


import com.alibaba.fastjson.JSON;
import com.spring.boot.common.utils.result.ResponseResult;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationFailureHandlerConfig implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ResponseResult result = new ResponseResult ();
        result.setSuccess(false);
        result.setMsg("Login Failure!");
        httpServletResponse.getWriter().write(JSON.toJSONString(result));
    }
}
