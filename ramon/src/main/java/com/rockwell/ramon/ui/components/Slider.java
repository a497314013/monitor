/*
 * @(#) Slider.java Sep 30, 2018 12:00:15 PM
 *
 * Copyright 2018 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.rockwell.ramon.ui.components;

import com.vaadin.flow.component.html.Input;

public class Slider extends Input
{
	private static final long serialVersionUID = -6486529158347923372L;

	public Slider(int min, int max)
	{
		setType(
			"range");
		getElement().setProperty(
			"min",
			min);
		getElement().setProperty(
			"max",
			max);
	}

	public Slider setOnValue(Integer value)
	{
		super.setValue(
			String.valueOf(
				value));
		return this;
	}

	public Integer getOnValue()
	{
		return Integer.valueOf(
			super.getValue());
	}

}
