package com.oauth.sys.persist.controller;


import com.oauth.sys.persist.service.SysUserService;
import com.spring.boot.common.utils.result.ResponseResult;
import com.spring.boot.common.utils.result.ResponseResultConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Stsy
 * @Date: 2018/11/23 14:30
 */
@Slf4j
@RestController
@RequestMapping("user/role/resource")
@PreAuthorize("hasAuthority('ADMIN')")
public class SysUserRoleResourceController {

    @Autowired
    private SysUserService service;

    @GetMapping("/{id}")
    protected ResponseResult selectByUserId(@PathVariable Long id) {
        ResponseResult result = new ResponseResult();
        try {
            result.setData(service.selectRoleResourceByPrimaryKey(id));
            result.setSuccessResponseResult(ResponseResultConstant.SELECT_SUCCESS);
            log.info(ResponseResultConstant.SELECT_SUCCESS);
        } catch (Exception ex) {
            result.setFailureResponseResult(ResponseResultConstant.SELECT_FAIL,ex);
            log.info(ResponseResultConstant.SELECT_FAIL);
        }
        return result;
    }
}
