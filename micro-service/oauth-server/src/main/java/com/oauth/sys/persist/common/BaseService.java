package com.oauth.sys.persist.common;


import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.List;

public interface BaseService<T extends BaseEntity, E extends PageInfo, ID  extends Serializable> {

    boolean insert(T record) throws Exception;

    boolean insertSelective(T record) throws Exception;

    boolean updateByPrimaryKeySelective(T record) throws Exception;

    boolean deleteByPrimaryKey(ID id) throws Exception;

    T selectByPrimaryKey(ID id) throws Exception;

    List<T> selectAllByExample(E example) throws Exception;

    PageInfo<T> selectPageByExample(E example) throws Exception;
}
