package com.oauth.sys.security.component;


import com.alibaba.fastjson.JSON;
import com.spring.boot.common.utils.result.JpaResponseResult;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccessDeniedHandlerConfig implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        JpaResponseResult result = new JpaResponseResult ();
        result.setSuccess(false);
        result.setMsg("Need Authorities!");
        httpServletResponse.getWriter().write(JSON.toJSONString(result));
    }
}