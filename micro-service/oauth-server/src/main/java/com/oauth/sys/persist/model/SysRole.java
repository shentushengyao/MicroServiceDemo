package com.oauth.sys.persist.model;

import com.oauth.sys.persist.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.List;

@Data
@Alias("SysRole")
@NoArgsConstructor
public class SysRole extends BaseEntity implements Serializable {

    private String name;

    private String discription;

    private static final long serialVersionUID = 1L;

    private List<SysResource> resources;
    public SysRole(final String name, final String discription) {
        this.name = name;
        this.discription = discription;
    }

}