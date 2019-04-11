package com.spring.boot.jpa.repository;


import com.oauth.sys.entity.SysUser;
import com.spring.boot.common.jpa.presist.common.base.JpaBaseReposiroty;

import java.util.Optional;

/**
 * @Author: Stsy
 * @Date: 2018/11/23 14:05
 */
public interface SysUserReposiroty extends JpaBaseReposiroty<SysUser, Long> {
    boolean existsByUsername(String username);
    Optional<SysUser> findFirstByUsername(String username);
}
