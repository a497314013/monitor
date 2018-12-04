/*
 * @(#) UnkownExceptionView.java 2018年11月12日 下午7:30:38
 *
 * Copyright 2018 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.rockwell.ramon.ui.views.exceptions;

import javax.servlet.http.HttpServletResponse;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.ErrorParameter;
import com.vaadin.flow.router.HasErrorParameter;

public class UnkownExceptionView extends VerticalLayout implements HasErrorParameter<Exception>
{
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1406555494818481100L;
	
	public UnkownExceptionView()
	{
		Label title = new Label();

		title.setText("Sorry, there is some unkown exception!");

		add(title);
	}
	
	public int setErrorParameter(BeforeEnterEvent event, ErrorParameter<Exception> parameter)
	{
		Label title = new Label();
		title.setText(parameter.getException().getMessage());
		add(title);
		return HttpServletResponse.SC_NO_CONTENT;
	}

}
