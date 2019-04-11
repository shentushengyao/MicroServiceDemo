package com.spring.boot.common.mybatis.persist.common;


import com.oauth.sys.persist.service.SysUserService;
import com.spring.boot.common.utils.ip.IpInfoUtil;
import com.spring.boot.common.utils.result.ResponseResult;
import com.spring.boot.common.utils.result.ResponseResultConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
public abstract class BaseController<S extends BaseService,T extends BaseEntity,ID extends Serializable> {


    @Autowired
    private S s;

    @Autowired
    SysUserService service;

    public S getS() {
        return s;
    }
    public SysUserService getUserS() {
        return service;
    }

    /**
     * 保存
     *
     * @param t
     */
    @PostMapping
    protected ResponseResult save(@RequestBody @Validated T t, BindingResult bindingResult, HttpServletRequest request, Principal principal) {
        ResponseResult result = new ResponseResult();
        try {
            Long id = service.selectByUsername(principal.getName()).getId();
            t.setModifyIp(IpInfoUtil.getIpAdrress(request));
            t.setCreateTime(LocalDateTime.now());
            t.setCreateBy(id);
            t.setModifyTime(LocalDateTime.now());
            t.setModifyBy(id);
            s.insert(t);
            result.setData(t);
            result.setSuccessResponseResult(ResponseResultConstant.SAVE_SUCCESS);
        } catch (Exception ex) {
            result.setFailureResponseResult(ResponseResultConstant.SAVE_FAIL,ex);
            errorCheck(bindingResult, result);
        }
        return result;
    }


    /**
     * 更新
     *
     * @param t
     */
    @PutMapping
    protected ResponseResult update(@RequestBody @Validated T t, BindingResult bindingResult, HttpServletRequest request, Principal principal) {
        ResponseResult result = new ResponseResult();
        try {
            Long id = service.selectByUsername(principal.getName()).getId();
            t.setModifyIp(IpInfoUtil.getIpAdrress(request));
            t.setModifyTime(LocalDateTime.now());
            t.setModifyBy(id);
            s.updateByPrimaryKeySelective(t);
            result.setData(t);
            result.setSuccessResponseResult(ResponseResultConstant.UPDATE_SUCCESS);
        } catch (Exception ex) {
            result.setFailureResponseResult(ResponseResultConstant.UPDATE_FAIL,ex);
            errorCheck(bindingResult, result);
        }
        return result;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    protected ResponseResult deleteById(@PathVariable ID id) {
        ResponseResult result = new ResponseResult();
        try {
            if(!s.deleteByPrimaryKey(id)){
                throw new Exception("此ID对应数据不存在");
            }
            result.setData(id);
            result.setSuccessResponseResult(ResponseResultConstant.DELETE_SUCCESS);
        } catch (Exception ex) {
            result.setFailureResponseResult(ResponseResultConstant.DELETE_FAIL,ex);
        }
        return result;
    }

    /**
     * 查询单条数据
     *
     * @param id id
     */
    /**
     * 查询一个
     *
     * @param id
     */
    @GetMapping("/{id}")
    protected ResponseResult selectByPrimaryKey(@PathVariable ID id) {
        ResponseResult result = new ResponseResult();
        try {
            result.setData(s.selectByPrimaryKey(id));
            result.setSuccessResponseResult(ResponseResultConstant.SELECT_SUCCESS);
            log.info(ResponseResultConstant.SELECT_SUCCESS);
        } catch (Exception ex) {
            result.setFailureResponseResult(ResponseResultConstant.SELECT_FAIL,ex);
            log.info(ResponseResultConstant.SELECT_FAIL);
        }
        return result;
    }

    protected void errorCheck(BindingResult bindingResult, ResponseResult ResponseResult) {
        StringBuilder sb = new StringBuilder();
        sb.append("; ");
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            ResponseResult.setData(errors);
        }
    }

}
