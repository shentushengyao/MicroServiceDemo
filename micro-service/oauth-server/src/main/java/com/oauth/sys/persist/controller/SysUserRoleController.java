package com.oauth.sys.persist.controller;


import com.oauth.sys.persist.common.BaseController;
import com.oauth.sys.persist.model.SysUserRole;
import com.oauth.sys.persist.service.SysUserRoleService;
import com.spring.boot.common.utils.ip.IpInfoUtil;
import com.spring.boot.common.utils.result.ResponseResult;
import com.spring.boot.common.utils.result.ResponseResultConstant;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.time.LocalDateTime;

/**
 * @Author: Stsy
 * @Date: 2018/11/23 14:30
 */
@RestController
@RequestMapping("user/role")
@PreAuthorize("hasAuthority('ADMIN')")
public class SysUserRoleController extends BaseController<SysUserRoleService, SysUserRole,Long> {

    /**
     * 保存
     *
     * @param t
     */
    @PostMapping
    protected ResponseResult save(@RequestBody @Validated SysUserRole t, BindingResult bindingResult, HttpServletRequest request, Principal principal) {
        ResponseResult result = new ResponseResult();
        try {
            getS().dumplicateSelect(t.getUserId(),t.getRoleId());
            Long id = getUserS().selectByUsername(principal.getName()).getId();
            t.setModifyIp(IpInfoUtil.getIpAdrress(request));
            t.setCreateTime(LocalDateTime.now());
            t.setCreateBy(id);
            t.setModifyTime(LocalDateTime.now());
            t.setModifyBy(id);
            getS().insert(t);
            result.setData(t);
            result.setSuccessResponseResult(ResponseResultConstant.SAVE_SUCCESS);
        } catch (Exception ex) {
            result.setFailureResponseResult(ResponseResultConstant.SAVE_FAIL,ex);
            errorCheck(bindingResult, result);
        }
        return result;
    }
}
