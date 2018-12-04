/*
 * @(#) MenuDao.java 2018年11月7日 下午4:05:14
 *
 * Copyright 2018 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.rockwell.ramon.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.rockwell.ramon.entity.Menu;

public interface MenuDao extends MongoRepository<Menu, String>,QuerydslPredicateExecutor<Menu>
{
	@Query("{}.sort({'name':1,'url':1})")
	List<Menu> findAllByOrderByNameAndUrl();
	Menu findByName(String name);
	List<Menu> findAllByOrderByUrl();
	List<Menu> findAllByTypeOrderBySequence(Integer type);
	List<Menu> findByTypeAndSequenceGreaterThan(Integer type,Integer sequence);
	List<Menu> findAllByOrderByName();
	List<Menu> findByParentId(String parentId);
	List<Menu> findBySequenceOrderByCtimeDesc(Integer sequence);
	List<Menu> findBySequenceOrderByCtime(Integer sequence);
	List<Menu> findBySequenceOrderByNameDesc(Integer sequence);
	List<Menu> findByUrl(String url);
	List<Menu> findByType(Integer type);
	List<Menu> findByTypeOrderBySequence(Integer type);
}
