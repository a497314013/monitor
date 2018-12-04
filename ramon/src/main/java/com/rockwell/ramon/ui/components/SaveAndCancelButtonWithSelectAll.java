/*
 * @(#) SaveAndCancelButtonWithSelectAll.java 2018年11月25日 下午3:24:36
 *
 * Copyright 2018 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.rockwell.ramon.ui.components;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;

import lombok.Getter;

@Route("SaveAndCancelButtonWithSelectAll")
public class SaveAndCancelButtonWithSelectAll extends Div
{
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1681524747673538170L;
	
	@Getter
	private Button selectAll = new Button("全选");
	
	@Getter
	private Button reverse = new Button("反选");
	private HorizontalLayout layout = new HorizontalLayout();
	private SaveAndCancelButton saveAndCancelButton;
	
	public SaveAndCancelButtonWithSelectAll()
	{
		this(null,null);
	}
	
	public SaveAndCancelButtonWithSelectAll(ComponentEventListener<ClickEvent<Button>> saveListener,
		ComponentEventListener<ClickEvent<Button>> cancelListener)
	{
		saveAndCancelButton = new SaveAndCancelButton(saveListener, cancelListener,false);
		layout.setSizeUndefined();
		layout.setSpacing(true);
		layout.setPadding(false);
		layout.getStyle().set("float", "left");
		saveAndCancelButton.getStyle().set("float", "right");
		this.setWidth("100%");
		initSelectButton();
	}
	
	private void initSelectButton()
	{
		reverse.setVisible(false);
		selectAll.addClickListener(event->{
			selectAll.setVisible(false);
			reverse.setVisible(true);
		});
		
		reverse.addClickListener(event->{
			selectAll.setVisible(true);
			reverse.setVisible(false);
		});
		
		layout.add(selectAll,reverse);
		saveAndCancelButton.initButton();
		add(layout,saveAndCancelButton);
	}

	public void setSelectAllListener(ComponentEventListener<ClickEvent<Button>> selectAllListener)
	{
		selectAll.addClickListener(selectAllListener);
	}
	
	public void setReverseListener(ComponentEventListener<ClickEvent<Button>> reverseListener)
	{
		reverse.addClickListener(reverseListener);
	}
	
	public void setCancelListener(ComponentEventListener<ClickEvent<Button>> cancelListener)
	{
		saveAndCancelButton.setCancelListener(cancelListener);
	}
	
	public void setSaveListener(ComponentEventListener<ClickEvent<Button>> saveListener)
	{
		saveAndCancelButton.setSaveListener(saveListener);
	}
}
