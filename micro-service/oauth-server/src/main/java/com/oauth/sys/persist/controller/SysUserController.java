package com.oauth.sys.persist.controller;


import com.oauth.sys.persist.common.BaseController;
import com.oauth.sys.persist.model.SysUser;
import com.oauth.sys.persist.model.SysUserExample;
import com.oauth.sys.persist.model.SysUserValidation;
import com.oauth.sys.persist.service.SysUserService;
import com.spring.boot.common.utils.ip.IpInfoUtil;
import com.spring.boot.common.utils.result.ResponseResult;
import com.spring.boot.common.utils.result.ResponseResultConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.time.LocalDateTime;

/**
 * @Author: Stsy
 * @Date: 2018/11/23 14:30
 */
@Slf4j
@RestController
@RequestMapping("user")
@PreAuthorize("hasAuthority('ADMIN')")
public class SysUserController extends BaseController<SysUserService, SysUser,Long> {

    @Override
    public ResponseResult save(SysUser t, BindingResult bindingResult, HttpServletRequest request, Principal principal) {
        return null;
    }

    @PostMapping("adult")
    public ResponseResult save(@RequestBody @Validated(value = {SysUserValidation.Adult.class, SysUserValidation.Default.class}) SysUserValidation user, BindingResult bindingResult, HttpServletRequest request, Principal principal) {
        ResponseResult result = new ResponseResult();
        try {
            if (bindingResult.hasErrors()) {
                throw new Exception();
            }
            Long id = getS().selectByUsername(principal.getName()).getId();
            user.setModifyIp(IpInfoUtil.getIpAdrress(request));
            user.setCreateTime(LocalDateTime.now());
            user.setCreateBy(id);
            user.setModifyTime(LocalDateTime.now());
            user.setModifyBy(id);
            getS().insert(user);
            result.setData(user);
            result.setSuccessResponseResult(ResponseResultConstant.SAVE_SUCCESS);
        } catch (Exception ex) {
            result.setFailureResponseResult(ResponseResultConstant.SAVE_FAIL, ex);
            errorCheck(bindingResult, result);
        }
        return result;
    }

    @PostMapping("children")
    public ResponseResult saveChildren(@RequestBody @Validated({SysUserValidation.Children.class, SysUserValidation.Default.class}) SysUser user, BindingResult bindingResult) {
        ResponseResult result = new ResponseResult();
        try {
            if (bindingResult.hasErrors()) {
                throw new Exception();
            }
            getS().insert(user);
            result.setSuccessResponseResult(ResponseResultConstant.SAVE_SUCCESS);
        } catch (Exception ex) {
            result.setFailureResponseResult(ResponseResultConstant.SAVE_FAIL,ex);
            errorCheck(bindingResult, result);
        }
        return result;
    }


    /**
     * 查询所有数据byExample
     *
     * @param user user
     */
    @GetMapping("example")
    protected ResponseResult selectAllByExample(@RequestBody SysUser user) {
        ResponseResult result = new ResponseResult();
        try {
            SysUserExample example = new SysUserExample();
            if(user.getName()!=null){
                example.or().andUsernameEqualTo(user.getName());
            }
            if(user.getUsername()!=null){
                example.or().andUsernameEqualTo(user.getUsername());
            }
            if(user.getSex()!=null){
                example.or().andSexEqualTo(user.getSex());
            }
            result.setData(getS().selectAllByExample(example));
            result.setSuccessResponseResult(ResponseResultConstant.SELECT_SUCCESS);
            log.info(ResponseResultConstant.SELECT_SUCCESS);
        } catch (Exception ex) {
            result.setFailureResponseResult(ResponseResultConstant.SELECT_FAIL,ex);
            log.info(ResponseResultConstant.SELECT_FAIL);
        }
        return result;
    }

    /**
     * 查询PageByExample
     *
     * @param user user
     */
    @GetMapping("page")
    protected ResponseResult selectPageByExample(@RequestBody SysUser user) {
        ResponseResult result = new ResponseResult();
        try {
            SysUserExample example = new SysUserExample();
            if(user.getName()!=null){
                example.or().andUsernameEqualTo(user.getName());
            }
            if(user.getUsername()!=null){
                example.or().andUsernameEqualTo(user.getUsername());
            }
            if(user.getSex()!=null){
                example.or().andSexEqualTo(user.getSex());
            }
            result.setData(getS().selectAllByExample(example));
            result.setSuccessResponseResult(ResponseResultConstant.SELECT_SUCCESS);
        } catch (Exception ex) {
            result.setFailureResponseResult(ResponseResultConstant.SELECT_FAIL,ex);
        }
        return result;
    }

}
