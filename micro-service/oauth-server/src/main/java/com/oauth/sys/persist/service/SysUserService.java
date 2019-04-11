package com.oauth.sys.persist.service;


import com.oauth.sys.persist.common.BaseService;
import com.oauth.sys.persist.model.SysUser;
import com.oauth.sys.persist.model.SysUserExample;

public interface SysUserService extends BaseService<SysUser, SysUserExample,Long> {
    SysUser selectByUsername(String username);
    SysUser selectRoleResourceByPrimaryKey(Long id);
}
