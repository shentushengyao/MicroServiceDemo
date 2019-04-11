package com.oauth.sys.persist.service.impl;


import com.oauth.sys.persist.common.BaseMapper;
import com.oauth.sys.persist.common.BaseServiceImpl;
import com.oauth.sys.persist.mapper.SysUserRoleMapper;
import com.oauth.sys.persist.model.SysUserRole;
import com.oauth.sys.persist.model.SysUserRoleExample;
import com.oauth.sys.persist.service.SysUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRole, SysUserRoleExample,Long> implements SysUserRoleService {

    @Resource
    private SysUserRoleMapper mapper;

    @Override
    public BaseMapper<SysUserRole,SysUserRoleExample,Long> getMapper() { return mapper; }

    @Override
    public void dumplicateSelect(Long userId, Long roleId) throws Exception {
        SysUserRoleExample example = new SysUserRoleExample();
        example.or()
                .andUserIdEqualTo(userId);
        example.or()
                .andRoleIdEqualTo(roleId);
        if(mapper.countByExample(example)>=0){
            throw new RuntimeException("[userId:"+userId+" roleId:"+roleId+"]已存在");
        }
    }

}
