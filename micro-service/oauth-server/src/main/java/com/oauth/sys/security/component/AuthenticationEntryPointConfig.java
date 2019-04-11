package com.oauth.sys.security.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户没有登录时返回给前端的数据
 * @Author: Stsy
 * @Date: 2018/12/4 13:29
 */
@Component
public class AuthenticationEntryPointConfig implements AuthenticationEntryPoint {

    private final Logger log = LoggerFactory.getLogger(AuthenticationEntryPointConfig.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        // TODO Auto-generated method stub
        log.info("Pre-authenticated entry point called.Rejecting access");

        if(checkToken(request)){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,authException.getMessage());
        }else{
            response.sendRedirect("/login");
        }
        //response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Access Denied");
    }

    public static boolean checkToken(HttpServletRequest request) {
//        String ajaxFlag = request.getHeader("X-Requested-With");
        String authorization =  request.getHeader("Authorization");
        String token = StringUtils.isEmpty(ServletRequestUtils.getStringParameter(request, "token","")) ? authorization:"" ;
        if(StringUtils.isEmpty(token)) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

}