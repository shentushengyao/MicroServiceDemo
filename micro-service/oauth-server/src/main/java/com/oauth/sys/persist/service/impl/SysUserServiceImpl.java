package com.oauth.sys.persist.service.impl;


import com.oauth.sys.persist.common.BaseMapper;
import com.oauth.sys.persist.common.BaseServiceImpl;
import com.oauth.sys.persist.model.SysUser;
import com.oauth.sys.persist.model.SysUserExample;
import com.oauth.sys.persist.mapper.SysUserMapper;
import com.oauth.sys.persist.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser, SysUserExample, Long> implements SysUserService {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Resource
    private SysUserMapper mapper;

    @Override
    public BaseMapper<SysUser,SysUserExample, Long> getMapper() { return mapper; }

    public SysUser selectRoleResourceByPrimaryKey(Long id){
        return mapper.selectRoleResourceByPrimaryKey(id);
    }

    public SysUser selectByUsername(String username){
        SysUserExample example = new SysUserExample();
        example.or()
                .andUsernameEqualTo(username);
        List<SysUser> sysUsers = mapper.selectByExample(example);
        if(sysUsers.size()!=0){
            return sysUsers.get(0);
        }
        return null;
    }


    public boolean insert(SysUser user) throws Exception {
        SysUserExample example = new SysUserExample();
        example.or()
                .andUsernameEqualTo(user.getUsername());
        if(null != selectByUsername(user.getUsername())){
            throw new Exception("该用户名已存在");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        int result = mapper.insertSelective(user);
        return result == 1;
    }
}
