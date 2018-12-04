/*
 * @(#) MemberPage.java Sep 30, 2018 11:57:28 AM
 *
 * Copyright 2018 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.rockwell.ramon.ui.views;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "member", layout = MainAppLayout.class)
public class MemberPage extends VerticalLayout
{
	private static final long serialVersionUID = -3951342379250054941L;

	public MemberPage()
	{
		Label title = new Label();

		title.setTitle(
			"Member");
		title.setText(
			"Member view");

		add(
			title);
	}

}
