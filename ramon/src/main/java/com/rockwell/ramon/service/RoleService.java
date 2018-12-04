/*
 * @(#) RoleService.java 2018年11月12日 上午11:21:50
 *
 * Copyright 2018 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.rockwell.ramon.service;

import com.rockwell.ramon.dao.RoleDao;
import com.rockwell.ramon.entity.Role;

public interface RoleService
{
	RoleDao getRoleDao();
	void delete(Role role);
}
