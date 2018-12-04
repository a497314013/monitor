/*
 * @(#) MenuType.java 2018年11月27日 上午10:36:19
 *
 * Copyright 2018 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.rockwell.ramon.ui.entities;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MenuType
{
	private final Map<String,Integer> types = new HashMap<>();
	
	public MenuType()
	{
		types.put("超链接", 0);
		types.put("菜单", 1);
		types.put("按钮", 2);
	}
	
	public Integer getValue(String key)
	{
		return types.get(key);
	}
	
	public String getKey(Integer key)
	{
		Set<Entry<String,Integer>> entrySet = types.entrySet();
		for(Entry<String,Integer> entry : entrySet)
		{
			if(entry.getValue() == key)
			{
				return entry.getKey();
			}
		}
		
		return null;
	}
	
	
	public Collection<String> getValues()
	{
		return types.keySet();
	}
}
