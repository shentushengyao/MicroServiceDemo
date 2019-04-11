package com.oauth.sys.security.component;


import com.alibaba.fastjson.JSON;
import com.spring.boot.common.utils.result.JpaResponseResult;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: Stsy
 * @Date: 2018/12/4 13:28
 */
@Component
public class LogoutSuccessHandlerConfig implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        JpaResponseResult result = new JpaResponseResult ();
        result.setSuccess(true);
        result.setMsg("Logout Success!");
        httpServletResponse.getWriter().write(JSON.toJSONString(result));
    }

}
