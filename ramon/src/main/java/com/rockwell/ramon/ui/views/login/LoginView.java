/*
 * @(#) ToLoginView.java 2018年11月7日 下午3:22:02
 *
 * Copyright 2018 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.rockwell.ramon.ui.views.login;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;

@Route(value="login")
@Tag("login-view")
@PageTitle("User Login")
@HtmlImport("src/view/login/login-view.html")
public class LoginView extends PolymerTemplate<LoginView.Model> implements AfterNavigationObserver
{
	private static final long serialVersionUID = 7962310036549150689L;

	
	public LoginView()
	{
		/*if (SecurityUtils.isLoggedIn()) 
		{
			//load all menu after login success
			MenuCashe menuCashe = MenuCashe.getInstance();
			menuCashe.initLeftMenuCashe();
	    }*/
	}
	
	@Override
	public void afterNavigation(AfterNavigationEvent event) {
		boolean error = event.getLocation().getQueryParameters().getParameters().containsKey("error");
		getModel().setError(error);
	}
	
	public interface Model extends TemplateModel {
		void setError(boolean error);
	}
}
