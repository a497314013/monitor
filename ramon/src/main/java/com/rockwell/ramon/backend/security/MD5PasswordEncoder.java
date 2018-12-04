/*
 * @(#) MD5PasswordEncoder.java 2018年11月12日 下午6:06:29
 *
 * Copyright 2018 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.rockwell.ramon.backend.security;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.rockwell.ramon.backend.util.md5.EncryptionByMD5;

public class MD5PasswordEncoder implements PasswordEncoder
{
	@Override
	public String encode(CharSequence rawPassword)
	{
		return EncryptionByMD5.encryptionByMD5(rawPassword.toString().getBytes());
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword)
	{
		String _rawPassword = EncryptionByMD5.encryptionByMD5(rawPassword.toString().getBytes());
		if(_rawPassword.equals(encodedPassword))
		{
			return true;
		}
		
		throw new DisabledException("The username and password doesn't match!");
	}

}
