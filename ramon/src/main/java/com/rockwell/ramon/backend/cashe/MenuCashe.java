/*
 * @(#) MenuCashe.java 2018年11月12日 下午9:52:12
 *
 * Copyright 2018 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.rockwell.ramon.backend.cashe;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rockwell.ramon.dao.MenuDao;
import com.rockwell.ramon.entity.Menu;

import lombok.Getter;

@Repository
public class MenuCashe
{
	private static MenuCashe menuCashe = new MenuCashe();
	@Getter
	private List<Menu> leftMenuCashe = null;
	public final Integer LEFT_MENU_TYPE = 1;
	
	@Autowired
	private MenuDao menuDao;
	
	public MenuCashe()
	{
		
	}
	
	public static MenuCashe getInstance()
	{
		return menuCashe;
	}
	
	public void initLeftMenuCashe()
	{
		leftMenuCashe = new ArrayList<>();
		List<Menu> menus = menuDao.findByTypeOrderBySequence(LEFT_MENU_TYPE);
		if(menus == null || menus.size() <= 0)
		{
			return;	
		}
		menus.forEach(menu ->{
			leftMenuCashe.add(menu);
		});
	}
}
