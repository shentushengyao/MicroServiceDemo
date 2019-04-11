package com.spring.boot.springbootservice.sys.security.config;

import com.oauth.sys.persist.model.SysResource;
import com.oauth.sys.persist.model.SysRole;
import com.oauth.sys.persist.model.SysUser;
import com.oauth.sys.persist.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @Author: Stsy
 * @Date: 2018/12/3 16:22
 */
@Component
public class UserDetailsServiceConfig implements UserDetailsService {

    @Autowired
    private SysUserService service;

    /**
     * 根据用户名获取登录用户信息
     *
     * @param username
     * @return
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = service.selectByUsername(username);
        user = service.selectRoleResourceByPrimaryKey(user.getId());
        if (user != null) {
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            for (SysRole role : user.getRoles()) {
                    for(SysResource resource: role.getResources()){
                        //1：此处将权限信息添加到 GrantedAuthority 对象中，在后面进行全权限验证时会使用GrantedAuthority 对象。
                        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(resource.getUrl());
                        grantedAuthorities.add(grantedAuthority);
                    }
            }
            return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
        } else {
            throw new UsernameNotFoundException("admin: " + username + " do not exist!");
        }
    }


}