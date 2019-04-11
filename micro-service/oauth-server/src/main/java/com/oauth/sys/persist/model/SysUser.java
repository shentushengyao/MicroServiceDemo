package com.oauth.sys.persist.model;

import com.oauth.sys.persist.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.List;

@Data
@Alias("SysUser")
@NoArgsConstructor
public class SysUser extends BaseEntity implements Serializable {

    private String username;

    private String password;

    private String name;

    private Integer age;

    private Boolean sex;

    private static final long serialVersionUID = 1L;

    private List<SysRole> roles;

    public SysUser(final String username, final String password, final String name, final Integer age, final Boolean sex) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

}