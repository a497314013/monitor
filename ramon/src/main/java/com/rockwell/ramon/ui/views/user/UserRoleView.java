/*
 * @(#) MemberPage.java Sep 30, 2018 11:57:28 AM
 *
 * Copyright 2018 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.rockwell.ramon.ui.views.user;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.rockwell.ramon.dao.RoleDao;
import com.rockwell.ramon.dao.UserDao;
import com.rockwell.ramon.entity.Role;
import com.rockwell.ramon.entity.User;
import com.rockwell.ramon.ui.components.SaveAndCancelButtonWithSelectAll;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class UserRoleView extends VerticalLayout
{
	private static final long serialVersionUID = -3951342379250054941L;
	private RoleDao roleDao;
	private UserDao userDao;
	private Integer allRoleCount = 0;
	private List<Role> roles = new ArrayList<>();
	private List<Checkbox> checkBoxs = new ArrayList<>();
	private SaveAndCancelButtonWithSelectAll saveAndCancelButtonLayout;
	
	public void setCancelListener(ComponentEventListener<ClickEvent<Button>> cancelListener)
	{
		saveAndCancelButtonLayout.setCancelListener(cancelListener);
	}
	
	public void setSaveListener(ComponentEventListener<ClickEvent<Button>> saveListener)
	{
		saveAndCancelButtonLayout.setSaveListener(saveListener);
	}
	
	private User user;
	
	public UserRoleView(UserDao userDao,RoleDao roleDao,User user)
	{
		this.roleDao = roleDao;
		this.userDao = userDao;
		this.user = user;
		setSizeFull();
        setMargin(false);
        setPadding(false);
        setSpacing(true);
		initRoles();
	}
	
	private void initRoles()
	{
		FormLayout formLayout = new FormLayout();
		setFlexGrow(1, formLayout);
		formLayout.setSizeFull();
		List<Role> _roles = roleDao.findAllByOrderByRoleName();
		if(_roles == null || _roles.size() <= 0)
		{
			formLayout.add(new Label("No any role in system!"));
			add(formLayout);
			return;
		}
		allRoleCount = _roles.size();
		_roles.forEach(role->{
			Checkbox checkbox = new Checkbox();
			checkBoxs.add(checkbox);
			checkbox.setId(role.getId());
			checkbox.setLabel(role.getRoleName());
			if(user.getRoles() != null && user.getRoles().contains(role)) 
			{
				roles.add(role);
				checkbox.setValue(true);
			}
			checkbox.addValueChangeListener(event->{
				if(event.getValue() && !roles.contains(role))
				{
					roles.add(role);
				}
				else
				{
					if(roles.contains(role))
					{
						roles.remove(role);
					}
				}
			});
			formLayout.add(checkbox);
		});
		
		saveAndCancelButtonLayout = new SaveAndCancelButtonWithSelectAll();
		if(roles.size() == allRoleCount)
		{
			saveAndCancelButtonLayout.getSelectAll().click();
		}
		saveAndCancelButtonLayout.setSelectAllListener(event->checkBoxs.forEach(checkbox->checkbox.setValue(true)));
		saveAndCancelButtonLayout.setReverseListener(event->checkBoxs.forEach(checkbox->checkbox.setValue(false)));
		add(formLayout,saveAndCancelButtonLayout);
	}

	public User save()
	{
		user.setRoles(roles);
		if(roles.size() <= 0)
		{
			user.setRoles(null);;
		}
		user.setUTime(LocalDateTime.now());
		return userDao.save(user);
	}

}
