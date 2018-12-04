package com.rockwell.ramon.service;

import java.util.Collection;

import com.rockwell.ramon.entity.PageBean;
import com.rockwell.ramon.entity.SearchBean;

/**
 * TODO Please enter the description of this type. This is mandatory!
 * <p> 
 * @author Brian, 2018年10月26日 下午4:27:46
 */
public interface BaseService<T>
{

	/**
	 * 保存对像
	 * 
	 * @param entity
	 */
	void save(T entity);

	/**
	 * 更新对像
	 * 
	 * @param entity
	 */
	void update(T entity);

	/**
	 * 通过id查找
	 * 
	 * @param id
	 * @return
	 */
	T findObjectById(Integer id);

	/**
	 * 通过id 数组删除多行数据
	 * 
	 * @param ids
	 */
	void deleteByIds(Integer... ids);

	/**
	 * 通过id 数组删除多行数据
	 * 
	 * @param ids
	 */
	void deleteById(Integer id);

	/**
	 * 通过对像删除
	 * 
	 * @param entities
	 */
	void deleteAllObjects(Collection<T> entities);

	PageBean getList(SearchBean searchbean);

	/**
	 * 通过条件删除
	 * 
	 * @param whereHql
	 * @param params
	 * @param indead
	 *  是否真的删掉，true为真的删掉，数据库的内容都没了，否时只是标
	 */
	void deleteByCondition(String whereHql, Object[] params, boolean indead);

	/**
	 * 批量更新，标志更新
	 * 
	 * @param ids
	 */
	void updateStatusToDelete(Integer... ids);
}
