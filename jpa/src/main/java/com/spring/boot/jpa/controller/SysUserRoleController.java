package com.spring.boot.jpa.controller;


import com.oauth.sys.entity.SysRole;
import com.oauth.sys.entity.SysUser;
import com.spring.boot.common.utils.result.JpaResponseResult;
import com.spring.boot.common.utils.result.ResponseResultConstant;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @Author: Stsy
 * @Date: 2018/11/23 14:30
 */
@RestController
@PreAuthorize("hasAuthority('ADMIN')")
public class SysUserRoleController{

    @PersistenceContext
    private EntityManager em;


    @PostMapping("user/{userId}/role/{roleId}")
    @Transactional
    public JpaResponseResult save(@PathVariable Long userId,@PathVariable Long roleId) {
        JpaResponseResult result = new JpaResponseResult();
        try {
            SysUser sysUser = em.find(SysUser.class, userId);
            sysUser.addRole(em.getReference(SysRole.class,roleId));
            result.setSuccessResponseResult(ResponseResultConstant.SAVE_SUCCESS);
        } catch (Exception ex) {
            result.setFailureResponseResult(ResponseResultConstant.SAVE_FAIL, ex);
        }
        return result;
    }

}
