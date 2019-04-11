package com.spring.boot.common.jpa.presist.common;


import com.spring.boot.common.utils.ip.IpInfoUtil;
import com.spring.boot.common.utils.result.JpaResponseResult;
import com.spring.boot.common.utils.result.ResponseResultConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: Stsy
 * @Date: 2018/11/23 13:35
 */
@Slf4j
public abstract class JpaBaseController<T extends JpaBaseEntity, S extends JpaBaseReposiroty> {

    @Autowired
    private S s;

    @PersistenceContext
    private EntityManager em;

    /**
     * 查询一个
     *
     * @param id
     */
    @GetMapping("/{id}")
    @Transactional
    protected JpaResponseResult findById(@PathVariable Long id) {
        JpaResponseResult result = new JpaResponseResult();
        try {
            result.setData(s.findById(id));
            result.setSuccessResponseResult(ResponseResultConstant.SELECT_SUCCESS);
        } catch (Exception ex) {
            result.setFailureResponseResult(ResponseResultConstant.SELECT_FAIL, ex);
        }
        return result;
    }

    /**
     * 保存
     *
     * @param t
     */
    @PostMapping
    protected JpaResponseResult save(@RequestBody @Validated T t, BindingResult bindingResult, HttpServletRequest request) {
        JpaResponseResult result = new JpaResponseResult();
        try {
            t.setModifyIp(IpInfoUtil.getIpAdrress(request));
            s.save(t);
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
    @PutMapping("/{id}")
    protected JpaResponseResult update(@PathVariable Long id,@RequestBody @Validated T t, BindingResult bindingResult, HttpServletRequest request) {
        JpaResponseResult result = new JpaResponseResult();
        try {
            t.setModifyIp(IpInfoUtil.getIpAdrress(request));
            s.save(t);
            result.setSuccessResponseResult(ResponseResultConstant.SAVE_SUCCESS);
        } catch (Exception ex) {
            result.setFailureResponseResult(ResponseResultConstant.SAVE_FAIL,ex);
            errorCheck(bindingResult, result);
        }
        return result;
    }

    /**
     * 删除
     *
     * @param id
     */
    @DeleteMapping("/{id}")
    protected JpaResponseResult delete(@PathVariable Long id) {
        JpaResponseResult result = new JpaResponseResult();
        try {
            s.delete(id);
            result.setSuccessResponseResult(ResponseResultConstant.SAVE_SUCCESS);
        } catch (Exception ex) {
            result.setFailureResponseResult(ResponseResultConstant.SAVE_FAIL, ex);
        }
        return result;
    }

    public S getS() {
        return s;
    }

    protected void errorCheck(BindingResult bindingResult, JpaResponseResult result) {
        StringBuilder sb = new StringBuilder();
        sb.append("; ");
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            for (ObjectError err : errors) {
                sb.append(err.getDefaultMessage());
                sb.append("; ");
            }
            result.setMsg(result.getMsg() + sb.toString());
        }
    }

}
