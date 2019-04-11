package com.spring.boot.common.utils.result;

import com.spring.boot.common.utils.string.MyStringUtils;
import lombok.Data;

/**
 * @Author: Stsy
 * @Date: 2018/11/27 13:50
 */
@Data
public class ResponseResult {

    /**
     * false 失败 true 成功
     */
    private Boolean success = true;

    /**
     * 返回消息类型,用于复杂返回信息,0代表普通返回信息,1代表特殊返回信息
     */
    private Integer code = 0;
    /**
     * 返回信息
     */
    private String msg = "操作成功";

    /**
     * 返回信息
     */
    private Object data;

    /**
     * 返回信息
     */
    private String token;



    public ResponseResult() {
    }

    public void setSuccessResponseResult(String constant) {
        this.success = true;
        this.msg = constant;
    }

    public void setFailureResponseResult(String constant,Exception ex) {
        this.success = false;
        this.msg = constant+ MyStringUtils.ifBlank(ex.getMessage(),"");
    }
    public void setFailureResponseResult(String constant) {
        this.success = false;
        this.msg = constant;
    }
}
