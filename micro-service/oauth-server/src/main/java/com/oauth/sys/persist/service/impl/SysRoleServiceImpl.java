package com.oauth.sys.persist.service.impl;


import com.oauth.sys.persist.common.BaseMapper;
import com.oauth.sys.persist.common.BaseServiceImpl;
import com.oauth.sys.persist.model.SysRole;
import com.oauth.sys.persist.model.SysRoleExample;
import com.oauth.sys.persist.mapper.SysRoleMapper;
import com.oauth.sys.persist.service.SysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole, SysRoleExample, Long> implements SysRoleService {

    @Resource
    private SysRoleMapper mapper;

    @Override
    public BaseMapper<SysRole,SysRoleExample, Long> getMapper() { return mapper; }

    @Override
    public boolean existByName(String name) throws Exception {
        SysRoleExample example = new SysRoleExample();
        example.or()
                .andNameEqualTo(name);
        long count = mapper.countByExample(example);
        return count >= 1;
    }

    public boolean insert(SysRole role) throws Exception {
        if(existByName(role.getName())){
            throw new Exception(role.getName()+"该角色名已存在");
        }
        int result = mapper.insertSelective(role);
        return result == 1;
    }

}
