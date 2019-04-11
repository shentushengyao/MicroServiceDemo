package com.spring.boot.jpa.controller;


import com.oauth.sys.entity.SysRole;
import com.oauth.sys.repository.SysRoleReposiroty;
import com.spring.boot.common.jpa.presist.common.base.JpaBaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Stsy
 * @Date: 2018/11/23 14:30
 */
@RestController
@RequestMapping("role")
public class SysRoleController extends JpaBaseController<SysRole, SysRoleReposiroty> {
}
