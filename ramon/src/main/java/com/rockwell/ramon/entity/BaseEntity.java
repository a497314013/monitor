/*
 * @(#) BaseEntity.java 2018年11月22日 下午10:29:04
 *
 * Copyright 2018 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.rockwell.ramon.entity;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class BaseEntity
{
	private LocalDateTime cTime;
	private LocalDateTime uTime;
	
	public void setCTime(LocalDateTime cTime)
	{
		if(cTime != null)
		{
			this.cTime = cTime;
		}
		else
		{
			this.cTime = LocalDateTime.now();
		}
	}
	
	public void setUTime(LocalDateTime uTime)
	{
		if(uTime != null)
		{
			this.uTime = uTime;
		}
		else
		{
			this.uTime = LocalDateTime.now();
		}
	}
}
