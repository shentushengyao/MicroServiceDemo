package com.spring.boot.jpa.controller;


import com.oauth.sys.entity.SysUser;
import com.oauth.sys.repository.SysUserReposiroty;
import com.spring.boot.common.jpa.presist.common.base.JpaBaseController;
import com.spring.boot.common.utils.ip.IpInfoUtil;
import com.spring.boot.common.utils.result.JpaResponseResult;
import com.spring.boot.common.utils.result.ResponseResultConstant;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Stsy
 * @Date: 2018/11/23 14:30
 */
@RestController
@RequestMapping("user")
@PreAuthorize("hasAuthority('ADMIN')")
public class SysUserController extends JpaBaseController<SysUser, SysUserReposiroty> {

    @PersistenceContext
    private EntityManager em;

    @Override
    @PostMapping(value = "adult")
    public JpaResponseResult save(@RequestBody @Validated({SysUser.Adult.class, SysUser.Default.class}) SysUser user, BindingResult bindingResult, HttpServletRequest request) {
        JpaResponseResult result = new JpaResponseResult();
        try {
            if (bindingResult.hasErrors()) {
                throw new Exception();
            }
            user.setModifyIp(IpInfoUtil.getIpAdrress(request));
            getS().save(user);
            result.setData(user);
            result.setSuccessResponseResult(ResponseResultConstant.SAVE_SUCCESS);
        } catch (Exception ex) {
            result.setFailureResponseResult(ResponseResultConstant.SAVE_FAIL, ex);
            errorCheck(bindingResult, result);
        }
        return result;
    }

    @PostMapping("children")
    public JpaResponseResult saveChildren(@RequestBody @Validated({SysUser.Children.class, SysUser.Default.class}) SysUser user, BindingResult bindingResult) {
        JpaResponseResult result = new JpaResponseResult();
        try {
            if (bindingResult.hasErrors()) {
                throw new Exception();
            }
            getS().save(user);
            result.setSuccessResponseResult(ResponseResultConstant.SAVE_SUCCESS);
        } catch (Exception ex) {
            result.setFailureResponseResult(ResponseResultConstant.SAVE_FAIL, ex);
            errorCheck(bindingResult, result);
        }
        return result;
    }

    @GetMapping("/{id}")
    @Transactional
    protected JpaResponseResult findById(@PathVariable Long id) {
        JpaResponseResult result = new JpaResponseResult();
        try {
            SysUser sysUser = em.find(SysUser.class, id);
            result.setData(sysUser);
            result.setSuccessResponseResult(ResponseResultConstant.SELECT_SUCCESS);
        } catch (Exception ex) {
            result.setFailureResponseResult(ResponseResultConstant.SELECT_FAIL, ex);
        }
        return result;
    }

    @GetMapping
    public JpaResponseResult findByParam(@RequestParam("usernmae") String usernmae) {
        JpaResponseResult result = new JpaResponseResult();
        try {
            if (getS().existsByUsername(usernmae)) {
                result.setSuccessResponseResult(ResponseResultConstant.SELECT_SUCCESS);
            } else {
                result.setFailureResponseResult(ResponseResultConstant.NOT_EXIST);
            }
        } catch (Exception ex) {
            result.setFailureResponseResult(ResponseResultConstant.SELECT_FAIL, ex);
        }
        return result;
    }

}
