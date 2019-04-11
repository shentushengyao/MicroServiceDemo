/******************************************************************
 *
 *    Copyright (c) 2010-2017 Digital Telemedia Co.,Ltd
 *    https://home.cnblogs.com/u/skyme/
 *
 *    Package:     com.cloud.skyme.controller
 *
 *    Filename:    HelloController.java
 *
 *    Description: TODO(用一句话描述该文件做什么)
 *
 *    Copyright:   Copyright (c) 2010-2017
 *
 *    Company:     cloudskyme
 *
 *    @author: zhangfeng
 *
 *    @version: 1.0.0
 *
 *    Create at:   2017年10月23日 下午5:33:07
 *
 *    Revision:
 *
 *    2017年10月23日 下午5:33:07
 *        - first revision
 *
 *****************************************************************/
package com.spring.boot.samples.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangfeng
 * @version 1.0.0
 * @ClassName HelloController
 * @Description spring boot aop测试
 * @Date 2017年10月23日 下午5:33:07
 */
@RestController
@RequestMapping("/aop")
public class AopTestController {

    private final static Logger logger = LoggerFactory.getLogger(AopTestController.class);

    /**
     * @return
     * @Description 测试前置通知
     */
    @RequestMapping("/testBeforeService")
    public String testBeforeService() {
        return "前四通知测试";
    }

    /**
     * @return
     * @Description 后置通知测试
     */
    @RequestMapping("/testAfterReturning")
    public String testAfterReturning() {
        return "后置通知测试";
    }

    /**
     * @return
     * @Description 异常通知测试
     */
    @RequestMapping("/testAfterThrowing")
    public String testAfterThrowing() {
        throw new NullPointerException();
    }

    /**
     * @return
     * @Description 后置最终通知测试
     */
    @RequestMapping("/testAfter")
    public String testAfter() {
        return "后置最终通知测试";
    }

    /**
     * @return
     * @Description 环绕通知
     */
    @RequestMapping("/testAroundService")
    public String testAroundService() {
        return "环绕通知";
    }


}
