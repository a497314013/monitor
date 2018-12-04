/*
 * @(#) MenuService.java 2018年11月12日 下午2:04:19
 *
 * Copyright 2018 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.rockwell.ramon.service;

import java.util.List;

import com.rockwell.ramon.constant.SaveOperation;
import com.rockwell.ramon.dao.MenuDao;
import com.rockwell.ramon.entity.Menu;
import com.rockwell.ramon.entity.Role;

public interface MenuService
{
	List<Role> findRolesByMenuUrl(String url);
	MenuDao getMenuDao();
	void delete(Menu menu);
	Menu save(Menu menu);
	Menu save(Menu menu,SaveOperation operation);
}
