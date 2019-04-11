package com.spring.boot.common.jpa.presist.common;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @Author: Stsy
 * @Date: 2018/11/23 13:35
 */
public interface JpaBaseReposiroty<T,S> extends PagingAndSortingRepository<T,S>,JpaSpecificationExecutor<T> {
}
