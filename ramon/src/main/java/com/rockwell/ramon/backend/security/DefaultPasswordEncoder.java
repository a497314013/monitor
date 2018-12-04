/*
 * @(#) MD5PasswordEncoder.java 2018年11月12日 下午6:06:29
 *
 * Copyright 2018 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.rockwell.ramon.backend.security;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class DefaultPasswordEncoder implements PasswordEncoder
{
	@Override
	public String encode(CharSequence rawPassword)
	{
		return rawPassword.toString();
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword)
	{
		if(encodedPassword.equals(rawPassword))
		{
			return true;
		}
		
		throw new DisabledException("The username and password doesn't match!");
	}

}
