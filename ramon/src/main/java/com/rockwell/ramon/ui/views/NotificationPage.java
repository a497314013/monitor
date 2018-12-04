/*
 * @(#) NotificationPage.java Sep 30, 2018 12:30:58 PM
 *
 * Copyright 2018 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.rockwell.ramon.ui.views;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class NotificationPage extends VerticalLayout {
	private static final long serialVersionUID = 1333242352353376736L;

	public NotificationPage() {
		add(new H2("Notification"), new H5("Notification view"));
	}
}

