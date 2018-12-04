package com.rockwell.ramon.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

/**
 * TODO Please enter the description of this type. This is mandatory!
 * <p>
 * 
 * @author Brian, 2018年10月27日 上午1:01:05
 */
public interface BaseDao<T>
{

	Page<T> findAll(Specification<T> spec, Pageable pageable); // 分页按条件查询

	List<T> findAll(Specification<T> spec); // 不分页按条件查询

}
