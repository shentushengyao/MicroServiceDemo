package com.oauth.sys.security.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @Author: Stsy
 * @Date: 2018/12/3 16:22
 */
@Component
public class UserDetailsServiceConfig implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 根据用户名获取登录用户信息
     *
     * @param username
     * @return
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        Optional<SysUser> user = reposiroty.findFirstByUsername(username);
//        if (user.isPresent()) {
//            throw new UsernameNotFoundException("用户名：" + username + "不存在！");
//        }
//        Collection<SimpleGrantedAuthority> collection = new HashSet<>();
//        Iterator<SysRole> iterator = user.get().getRoles().iterator();
//
//        while (iterator.hasNext()) {
//            collection.add(new SimpleGrantedAuthority(iterator.next().getName()));
//        }
//        return new User(user.get().getUsername(), user.get().getPassword(), collection);
       return new User(username, passwordEncoder.encode("admin"), Arrays.asList(new SimpleGrantedAuthority("ADMIN")));

    }
}