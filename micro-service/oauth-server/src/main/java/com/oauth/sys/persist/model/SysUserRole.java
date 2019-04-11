package com.oauth.sys.persist.model;

import com.oauth.sys.persist.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Data
@Alias("SysUserRole")
@AllArgsConstructor
@NoArgsConstructor
public class SysUserRole extends BaseEntity implements Serializable {

    private Long userId;

    private Long roleId;

    private static final long serialVersionUID = 1L;

}