package com.oauth.sys.persist.model;

import com.oauth.sys.persist.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Data
@Alias("SysResource")
@AllArgsConstructor
@NoArgsConstructor
public class SysResource extends BaseEntity implements Serializable {

    private String url;

    private String discription;

    private static final long serialVersionUID = 1L;

}