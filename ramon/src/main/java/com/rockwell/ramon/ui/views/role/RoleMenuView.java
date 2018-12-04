/*
 * @(#) MemberPage.java Sep 30, 2018 11:57:28 AM
 *
 * Copyright 2018 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.rockwell.ramon.ui.views.role;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.rockwell.ramon.dao.MenuDao;
import com.rockwell.ramon.dao.RoleDao;
import com.rockwell.ramon.entity.Menu;
import com.rockwell.ramon.entity.Role;
import com.rockwell.ramon.ui.components.SaveAndCancelButtonWithSelectAll;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class RoleMenuView extends VerticalLayout
{
	private static final long serialVersionUID = -3951342379250054941L;
	private RoleDao roleDao;
	private MenuDao menuDao;
	private Integer allMenuCount = 0;
	private List<Menu> menus = new ArrayList<>();
	private List<Checkbox> checkBoxs = new ArrayList<>();
	private SaveAndCancelButtonWithSelectAll saveAndCancelButtonLayout;
	private Role role;
	
	public void setCancelListener(ComponentEventListener<ClickEvent<Button>> cancelListener)
	{
		saveAndCancelButtonLayout.setCancelListener(cancelListener);
	}
	
	public void setSaveListener(ComponentEventListener<ClickEvent<Button>> saveListener)
	{
		saveAndCancelButtonLayout.setSaveListener(saveListener);
	}
	
	public RoleMenuView(RoleDao roleDao,MenuDao menuDao,Role role)
	{
		this.roleDao = roleDao;
		this.menuDao = menuDao;
		this.role = role;
		setSizeFull();
        setMargin(false);
        setPadding(false);
        setSpacing(true);
		initMenus();
	}
	
	private void initMenus()
	{
		FormLayout formLayout = new FormLayout();
		setFlexGrow(1, formLayout);
		formLayout.setSizeFull();
		List<Menu> _menus = menuDao.findAllByOrderByName();
		if(_menus == null || _menus.size() <= 0)
		{
			formLayout.add(new Label("No any menu in system!"));
			add(formLayout);
			return;
		}
		allMenuCount = _menus.size();
		_menus.forEach(menu->{
			Checkbox checkbox = new Checkbox();
			checkBoxs.add(checkbox);
			String menuName = menu.getName();
        	if(menu.getUrl() != null && !"".equals(menu.getUrl()))
        	{
        		menuName += " </"+menu.getUrl()+">";
        	}
			checkbox.setLabel(menuName);
			
			if(role.getMenus() != null && role.getMenus().contains(menu)) 
			{
				menus.add(menu);
				checkbox.setValue(true);
			}
			checkbox.addValueChangeListener(event->{
				if(event.getValue() && !menus.contains(menu))
				{
					menus.add(menu);
				}
				else
				{
					if(menus.contains(menu))
					{
						menus.remove(menu);
					}
				}
			});
			formLayout.add(checkbox);
		});
		
		saveAndCancelButtonLayout = new SaveAndCancelButtonWithSelectAll();
		if(menus.size() == allMenuCount)
		{
			saveAndCancelButtonLayout.getSelectAll().click();
		}
		saveAndCancelButtonLayout.setSelectAllListener(event->checkBoxs.forEach(checkbox->checkbox.setValue(true)));
		saveAndCancelButtonLayout.setReverseListener(event->checkBoxs.forEach(checkbox->checkbox.setValue(false)));
//		setHorizontalComponentAlignment(Alignment.END, saveAndCancelButtonLayout);
		add(formLayout,saveAndCancelButtonLayout);
	}

	public Role save()
	{
		role.setMenus(menus);
		if(menus.size() <= 0)
		{
			role.setMenus(null);;
		}
		role.setUTime(LocalDateTime.now());
		return roleDao.save(role);
	}

}
