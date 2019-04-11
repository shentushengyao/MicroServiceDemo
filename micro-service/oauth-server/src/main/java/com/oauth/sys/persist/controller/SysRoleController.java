package com.oauth.sys.persist.controller;


import com.oauth.sys.persist.common.BaseController;
import com.oauth.sys.persist.model.SysRole;
import com.oauth.sys.persist.service.SysRoleService;
import com.oauth.sys.persist.service.SysUserService;
import com.spring.boot.common.utils.ip.IpInfoUtil;
import com.spring.boot.common.utils.result.ResponseResult;
import com.spring.boot.common.utils.result.ResponseResultConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.time.LocalDateTime;

/**
 * @Author: Stsy
 * @Date: 2018/11/23 14:30
 */
@RestController
@RequestMapping("role")
@PreAuthorize("hasAuthority('ADMIN')")
public class SysRoleController extends BaseController<SysRoleService, SysRole,Long> {

    @Autowired
    private SysUserService service;

    /**
     * 保存
     *
     * @param role
     */
    @RequestMapping(method = RequestMethod.POST)
    protected ResponseResult save(@RequestBody @Validated SysRole role, BindingResult bindingResult, HttpServletRequest request, Principal principal) {
        ResponseResult result = new ResponseResult();
        try {
            Long id = service.selectByUsername(principal.getName()).getId();
            role.setModifyIp(IpInfoUtil.getIpAdrress(request));
            role.setCreateTime(LocalDateTime.now());
            role.setCreateBy(id);
            role.setModifyTime(LocalDateTime.now());
            role.setModifyBy(id);
            getS().insert(role);
            result.setData(role);
            result.setSuccessResponseResult(ResponseResultConstant.SAVE_SUCCESS);
        } catch (Exception ex) {
            result.setFailureResponseResult(ResponseResultConstant.SAVE_FAIL,ex);
            errorCheck(bindingResult, result);
        }
        return result;
    }


}
