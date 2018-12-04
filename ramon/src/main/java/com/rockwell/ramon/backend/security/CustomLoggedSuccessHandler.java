/*
 * @(#) CustomLoggedSuccessHandler.java 2018年11月13日 下午2:57:11
 *
 * Copyright 2018 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.rockwell.ramon.backend.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.rockwell.ramon.backend.cashe.MenuCashe;

public class CustomLoggedSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler
{
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
		Authentication authentication)
		throws ServletException,
		IOException
	{
		//load the menu cashe
		MenuCashe menuCashe = MenuCashe.getInstance();
		menuCashe.initLeftMenuCashe();
		
		super.onAuthenticationSuccess(
			request,
			response,
			authentication);
	}
}
