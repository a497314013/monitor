/*
 * @(#) LoginController.java 2018年11月7日 下午4:37:57
 *
 * Copyright 2018 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.rockwell.ramon.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController
{
//	@RequestMapping("/toLogin")
	public String toLogin(HttpServletRequest request)
	{
		return "this is toLogin page!";
	}
	
//	@RequestMapping("/login")
	public String login(HttpServletRequest request)
	{
		return "this is login page!";
	}
	
	
}
