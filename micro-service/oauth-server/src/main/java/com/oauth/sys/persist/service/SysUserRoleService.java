package com.oauth.sys.persist.service;


import com.oauth.sys.persist.common.BaseService;
import com.oauth.sys.persist.model.SysUserRole;
import com.oauth.sys.persist.model.SysUserRoleExample;


public interface SysUserRoleService extends BaseService<SysUserRole, SysUserRoleExample,Long> {
    void dumplicateSelect(Long userId, Long roleId) throws Exception;
}
