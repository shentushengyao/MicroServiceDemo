package com.oauth.sys.security.component;


import com.alibaba.fastjson.JSON;
import com.spring.boot.common.utils.result.JpaResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class AuthenticationSuccessHandlerConfig implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        JpaResponseResult result = new JpaResponseResult ();
        result.setSuccess(true);
        result.setMsg("Login Success!");
        httpServletResponse.getWriter().write(JSON.toJSONString(result));

        //获取登陆成功用户名
        String username = ((UserDetails)authentication.getPrincipal()).getUsername();
        log.info("登陆成功:{}",username);
        List<String> authorities=((UserDetails) authentication.getPrincipal()).getAuthorities().stream().map(a->new String(((GrantedAuthority) a).getAuthority())).collect(Collectors.toList());
//        //登陆成功生成JWT
//        String token = Jwts.builder()
//                //主题 放入用户名
//                .setSubject(username)
//                //自定义属性 放入用户拥有权限
//                .claim(SecurityConstant.AUTHORITIES.getCode(), JSONObject.toJSONString(authorities))
//                //失效时间 7天
//                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 24 *7))
//                //签名算法和密钥
//                .signWith(SignatureAlgorithm.HS512,SecurityConstant.JWT_SIGN_KEY.getCode())
//                .compact();
//        token = SecurityConstant.TOKEN_SPLIT.getCode() + token;
//
//        String msg=JSON.toJSONString(ServerResponse.Success(token));
//        ServerResponse.out(response,msg);
    }
}
