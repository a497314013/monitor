/*
 * @(#) ViewRouter.java 2018年11月29日 下午1:26:12
 *
 * Copyright 2018 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.rockwell.ramon.constant;

import java.util.HashMap;
import java.util.Map;

import com.rockwell.ramon.ui.views.HomePage;
import com.rockwell.ramon.ui.views.menu.MenuView;
import com.rockwell.ramon.ui.views.role.RoleView;
import com.rockwell.ramon.ui.views.user.MutilUserView;
import com.rockwell.ramon.ui.views.user.UserView;
import com.vaadin.flow.component.Component;

public class ViewRouter
{
	private static ViewRouter viewRouter = new ViewRouter();
	/*
	 * String: URL
	 * Class: view class
	 */
	private static Map<String,Class<? extends Component>> viewRouterTable = new HashMap<>();
	
	static
	{
		viewRouterTable.put("", HomePage.class);
		viewRouterTable.put("user", UserView.class);
		viewRouterTable.put("user", MutilUserView.class);
		viewRouterTable.put("role", RoleView.class);
		viewRouterTable.put("menu", MenuView.class);
	}
	
	private ViewRouter() 
	{
	}
	
	public static ViewRouter getInstance()
	{
		return viewRouter;
	}
	
	public static Class<? extends Component> getViewClass(String url)
	{
		return viewRouterTable.get(url);
	}
}
