/*
 * @(#) HomePage.java Sep 30, 2018 12:01:46 PM
 *
 * Copyright 2018 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.rockwell.ramon.ui.views;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "", layout = MainAppLayout.class)
public class HomePage extends VerticalLayout {
	private static final long serialVersionUID = 1337339618913376736L;

	public HomePage() {
		add(new H2("Home"), new H5("Home view"));
	}
}
