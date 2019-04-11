package com.spring.boot.common.mybatis.persist.common;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Transient;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author: Stsy
 * @Dat 2018/11/23 11:33
 */
@Data
@NoArgsConstructor
public abstract class BaseEntity implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 创建者
     */
    private Long createBy;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 修改者
     */
    private Long modifyBy;
    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;
    /**
     * 发生修改IP
     */
    private String modifyIp;
    /**
     * 是否删除flags
     */
    private boolean isDelete = false;
    /**
     * 版本
     */
    public int version;

    @Transient
    public static String entityName;


}
