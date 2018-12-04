/*
 * @(#) RoleServiceImpl.java 2018年11月12日 上午11:18:26
 *
 * Copyright 2018 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.rockwell.ramon.service.impl;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rockwell.ramon.dao.RoleDao;
import com.rockwell.ramon.dao.UserDao;
import com.rockwell.ramon.entity.QUser;
import com.rockwell.ramon.entity.Role;
import com.rockwell.ramon.entity.User;
import com.rockwell.ramon.service.RoleService;

@Service("RoleService")
public class RoleServiceImpl implements RoleService
{

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private UserDao userDao;

	@Override
	public RoleDao getRoleDao()
	{
		return this.roleDao;
	}

	@Override
	public void delete(Role role)
	{
		QUser quser = QUser.user;
		Iterable<User> users = userDao.findAll(quser.roles.contains(role));
		if(users == null)
		{
			roleDao.delete(role);
			return;
		}
		
		Iterator<User> iterator = users.iterator();
		while(iterator.hasNext())
		{
			User user = iterator.next();
			user.getRoles().remove(role);
			userDao.save(user);
		}
		
		roleDao.delete(role);
	}

}
