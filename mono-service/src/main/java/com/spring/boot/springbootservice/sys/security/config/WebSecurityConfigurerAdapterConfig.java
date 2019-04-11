package com.spring.boot.springbootservice.sys.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

@EnableWebSecurity
public class WebSecurityConfigurerAdapterConfig extends WebSecurityConfigurerAdapter {
    private String loginPage = "/userLogin";
    private String failureUrl = "/userLogin?error=T";
    private String successUrl = "/userLogin?error=F";
    private String applyCheckCode = "/applyCheckCode";

    @Autowired
    MenuService menuService;

    public WebSecurityConfigurerAdapterConfig() {
        super(true);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.securityContext().securityContextRepository(securityContextRepository());
        //从数据库读取对应的url地址及权限角色
        List<MenuBean>  menuBeanList =  menuService.getMenus();
        for(MenuBean tmpBean : menuBeanList) {
            http.authorizeRequests().antMatchers(tmpBean.getMenuUrl()).hasAnyRole(tmpBean.getRoleIds().split(","));
        }
        //设置登录界面
        http.authorizeRequests()
                .and()
                //增加一个对验证码进行判断的filte
                .addFilterBefore(validCodeProcessingFilter(), UsernamePasswordAuthenticationFilter.class)
                .formLogin().loginPage(getLoginPage()).failureUrl(getFailureUrl())
                .failureForwardUrl(getFailureUrl()).
                successForwardUrl(getSuccessUrl()).permitAll();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        //设置根据用户名获取用户信息的自定义userDetailsService类,该类从数据库中读用用户信息
        auth.userDetailsService(userDetailsService());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        web.ignoring().antMatchers(applyCheckCode); //设置申请验证码的url不进行访问控制
    }

    @SuppressWarnings("deprecation")
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        //这里使用了密码不进行加密验证，正式项目还是必须要用加密验证方式
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
    @Bean
    public UserDetailsService userDetailsService() {
 UserDetailsServiceConfig myUserDetailsService = new UserDetailsServiceConfig(); return myUserDetailsService;
 }
    @Bean
    public SecurityContextRepository securityContextRepository() {
        //设置对spring security的UserDetails进行session保存,这个必须要有，不然不会保存至session对应的缓存redis中
        HttpSessionSecurityContextRepository httpSessionSecurityContextRepository =
                new HttpSessionSecurityContextRepository();
        return httpSessionSecurityContextRepository;
    }

    @Bean
    public  MyValidCodeProcessingFilter validCodeProcessingFilter() throws Exception{
        //设置对应的验证码验证filter,上面configure方法中通过
        //.addFilterBefore(validCodeProcessingFilter(),UsernamePasswordAuthenticationFilter.class)
        //将该filter放至UsernamePasswordAuthenticationFilter之间
        MyValidCodeProcessingFilter myValidCodeProcessingFilter = new MyValidCodeProcessingFilter(getLoginPage());
        myValidCodeProcessingFilter.getFailureHandler().setDefaultFailureUrl(getFailureUrl());
        myValidCodeProcessingFilter.setAuthenticationManager(this.authenticationManager());

        return myValidCodeProcessingFilter;
    }

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public String getFailureUrl() {
        return failureUrl;
    }

    public void setFailureUrl(String failureUrl) {
        this.failureUrl = failureUrl;
    }

    public String getSuccessUrl() {
        return successUrl;
    }

    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }

    public String getApplyCheckCode() {
        return applyCheckCode;
    }

    public void setApplyCheckCode(String applyCheckCode) {
        this.applyCheckCode = applyCheckCode;
    }
}