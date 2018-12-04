/*
 * @(#) DefaultPage.java Sep 30, 2018 11:54:30 AM
 *
 * Copyright 2018 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.rockwell.ramon.ui.views.exceptions;

import com.rockwell.ramon.ui.views.MainAppLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Access denied!")
@Route(value = "access-error", layout = MainAppLayout.class)
public class AccessDeniedExceptionView extends VerticalLayout
{
	private static final long serialVersionUID = 526172191959497669L;

	public AccessDeniedExceptionView()
	{
		Label title = new Label();

		title.setText("Sorry, you have no access for this page!");

		add(title);
	}
}
