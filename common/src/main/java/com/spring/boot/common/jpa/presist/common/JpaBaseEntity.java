package com.spring.boot.common.jpa.presist.common;


import lombok.Data;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author: Stsy
 * @Dat 2018/11/23 11:33
 */
@Data
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class JpaBaseEntity {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 创建者
     */
    @CreatedBy
    private Long createBy;
    /**
     * 创建时间
     */
    @CreatedDate
    private LocalDateTime createTime;
    /**
     * 修改者
     */
    @LastModifiedBy
    private Long modifyBy;
    /**
     * 修改时间
     */
    @LastModifiedDate
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
    @Version
    public int version;

    @Transient
    public static String entityName;

}
