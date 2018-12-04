/*
 * @(#) SettingsPage.java Sep 30, 2018 11:53:16 AM
 *
 * Copyright 2018 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.rockwell.ramon.ui.views;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "settings", layout = MainAppLayout.class)
public class SettingsPage extends VerticalLayout
{
	private static final long serialVersionUID = -2129831803083629071L;

	public SettingsPage() {
		Label title = new Label();
		
		title.setTitle("Settings");
		title.setText("Settings view");
		
		add(title);
	}

}
