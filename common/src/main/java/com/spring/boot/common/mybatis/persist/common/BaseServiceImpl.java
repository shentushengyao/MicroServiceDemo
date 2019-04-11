package com.spring.boot.common.mybatis.persist.common;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public abstract class BaseServiceImpl<T extends BaseEntity, E extends PageInfo, ID extends Serializable> implements BaseService<T, E, ID> {

    public abstract BaseMapper<T, E, ID> getMapper();

    @Override
    public boolean insert(T record) throws Exception {
        return getMapper().insert(record) > 0;
    }

    @Override
    public boolean insertSelective(T record) throws Exception {
        return getMapper().insertSelective(record) > 0;
    }

    @Override
    public boolean updateByPrimaryKeySelective(T record) throws Exception {
        return getMapper().updateByPrimaryKeySelective(record) > 0;
    }

    @Override
    public boolean deleteByPrimaryKey(ID id) throws Exception {
        return getMapper().deleteByPrimaryKey(id) > 0;
    }

    @Override
    public T selectByPrimaryKey(ID id) throws Exception {
        return getMapper().selectByPrimaryKey(id);
    }

    @Override
    public List<T> selectAllByExample(E example) throws Exception {
        return getMapper().selectByExample(example);
    }

    @Override
    public PageInfo<T> selectPageByExample(E example) throws Exception {
        PageHelper.startPage(example.getPageNum(), example.getPageSize());
        List<T> ts = getMapper().selectByExample(example);
        PageInfo<T> result = new PageInfo<>(ts);
        return result;
    }

}
