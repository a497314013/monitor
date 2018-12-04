/*
 * @(#) MenuServiceImpl.java 2018年11月12日 下午2:05:34
 *
 * Copyright 2018 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.rockwell.ramon.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rockwell.crudui.crud.CrudOperationException;
import com.rockwell.ramon.constant.SaveOperation;
import com.rockwell.ramon.dao.MenuDao;
import com.rockwell.ramon.dao.RoleDao;
import com.rockwell.ramon.entity.Menu;
import com.rockwell.ramon.entity.QMenu;
import com.rockwell.ramon.entity.QRole;
import com.rockwell.ramon.entity.Role;
import com.rockwell.ramon.service.MenuService;
import com.rockwell.ramon.ui.entities.MenuTypeEnum;

@Service("MenuService")
public class MenuServiceImpl implements MenuService
{
	@Autowired
	private MenuDao menuDao;
	
	@Autowired
	private RoleDao roleDao;
	
	public List<Role> findRolesByMenuUrl(String url)
	{
		List<Role> roles = new ArrayList<>();
		QRole qRole = QRole.role;
		QMenu qMenu = QMenu.menu;
		Iterable<Menu> menus = menuDao.findAll(qMenu.url.eq(url));
		if(menus != null)
		{
			Iterator<Menu> _menus = menus.iterator();
			while(_menus.hasNext())
			{
				Menu menu = _menus.next();
				Iterable<Role> findall = roleDao.findAll(qRole.menus.contains(menu));
				if(findall == null)
				{
					continue;
				}
				findall.forEach(roles::add);
			}
		}
		
		return roles;
	}
	
	@Override
	public MenuDao getMenuDao()
	{
		return this.menuDao;
	}

	@Override
	public void delete(Menu menu)
	{
		List<Menu> menus = menuDao.findByTypeAndSequenceGreaterThan(MenuTypeEnum.菜单.ordinal(), menu.getSequence());
		if(menus != null && menus.size() > 0)
		{
			menus.forEach(_menu -> _menu.setSequence(_menu.getSequence()-1));
			menuDao.saveAll(menus);
		}
		
		QRole qRole = QRole.role;
		Iterable<Role> roles = roleDao.findAll(qRole.menus.contains(menu));
		if(roles == null)
		{
			menuDao.delete(menu);
			return;
		}
		Iterator<Role> iterator = roles.iterator();
		while(iterator.hasNext())
		{
			Role role = iterator.next();
			role.getMenus().remove(menu);
			roleDao.save(role);
		}
		
		menuDao.delete(menu);
	}

	private Menu _save(Menu menu)
	{
		if("".equals(menu.getId()))
		{
			menu.setCtime(LocalDateTime.now());
		}
		menu.setUtime(LocalDateTime.now());
		return menuDao.save(menu);
	}

	@Override
	public Menu save(Menu menu)
	{
		if(menu.getParentId() != null && !"".equals(menu.getParentId()))
		{
			List<Menu> menus = menuDao.findByTypeAndSequenceGreaterThan(MenuTypeEnum.菜单.ordinal(), menu.getSequence()-1);
			if(menus != null && menus.size() > 0)
			{
				menus.forEach(_menu -> _menu.setSequence(_menu.getSequence()+1));
				menuDao.saveAll(menus);
			}
		}
		
		return _save(menu);
	}

	@Override
	public Menu save(Menu menu, SaveOperation operation)
	{
		if(SaveOperation.ADD.equals(operation))
		{
			if(menuDao.findByName(menu.getName()) != null)
			{
				throw(new CrudOperationException("the menu'name <"+menu.getName()+"> has already exist in the system!"));
			}
		}
		return save(menu);
	}
}
