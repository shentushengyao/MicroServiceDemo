package com.oauth.sys.persist.service;


import com.oauth.sys.persist.common.BaseService;
import com.oauth.sys.persist.model.SysRole;
import com.oauth.sys.persist.model.SysRoleExample;

public interface SysRoleService extends BaseService<SysRole, SysRoleExample,Long> {
    boolean existByName(String username) throws Exception;
}
