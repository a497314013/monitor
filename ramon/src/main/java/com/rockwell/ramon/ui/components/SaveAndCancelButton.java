/*
 * @(#) SaveAndCancelButton.java 2018年11月23日 下午4:14:57
 *
 * Copyright 2018 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.rockwell.ramon.ui.components;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import lombok.Getter;

public class SaveAndCancelButton extends HorizontalLayout
{
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 7555048541876508322L;
	private ComponentEventListener<ClickEvent<Button>> saveListener;
	private ComponentEventListener<ClickEvent<Button>> cancelListener;
	
	@Getter
	private Button saveButton;
	
	@Getter
	private Button cancelButton;
	
	public SaveAndCancelButton(ComponentEventListener<ClickEvent<Button>> saveListener,
		ComponentEventListener<ClickEvent<Button>> cancelListener)
	{
		this.saveListener = saveListener;
		this.cancelListener = cancelListener;
		
		setSizeUndefined();
        setSpacing(true);
        setPadding(false);
        initButton();
	}
	
	public SaveAndCancelButton(ComponentEventListener<ClickEvent<Button>> saveListener,
		ComponentEventListener<ClickEvent<Button>> cancelListener,boolean isAutoInitButton)
	{
		this.saveListener = saveListener;
		this.cancelListener = cancelListener;
		
		setSizeUndefined();
        setSpacing(true);
        setPadding(false);
        if(isAutoInitButton)
        {
        	initButton();
        }
	}
	
	public SaveAndCancelButton()
	{
		setSizeUndefined();
        setSpacing(true);
        setPadding(false);
        initButton();
	}
	
	protected void initButton()
	{
		saveButton = new Button();
		saveButton.setText("Save");
		saveButton.setIcon(VaadinIcon.CHECK.create());
		saveButton.setIconAfterText(false);
		saveButton.getElement().setAttribute("theme","primary");
		if(this.saveListener != null)
		{
			saveButton.addClickListener(this.saveListener);
		}
		
		cancelButton = new Button();
		cancelButton.setText("Cancel");
		cancelButton.setIconAfterText(false);
		if(this.cancelListener != null)
		{
			cancelButton.addClickListener(this.cancelListener);
		}
		
		add(cancelButton,saveButton);
	}

	public void setSaveListener(ComponentEventListener<ClickEvent<Button>> saveListener)
	{
		saveButton.addClickListener(saveListener);
	}
	
	public void setCancelListener(ComponentEventListener<ClickEvent<Button>> cancelListener)
	{
		cancelButton.addClickListener(cancelListener);
	}
}
