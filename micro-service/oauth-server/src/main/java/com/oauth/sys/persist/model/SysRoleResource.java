package com.oauth.sys.persist.model;

import com.oauth.sys.persist.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Data
@Alias("SysRoleResource")
@AllArgsConstructor
@NoArgsConstructor
public class SysRoleResource extends BaseEntity implements Serializable {

    private Long roleId;

    private Long resourceId;

    private Boolean enable;

    private static final long serialVersionUID = 1L;

}