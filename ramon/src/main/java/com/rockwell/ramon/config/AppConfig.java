/*
 * @(#) AppConfig.java 2018年11月12日 下午11:34:58
 *
 * Copyright 2018 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.rockwell.ramon.config;

import javax.persistence.EntityManager;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.querydsl.jpa.HQLTemplates;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.rockwell.ramon.backend.cashe.MenuCashe;

@Configuration
public class AppConfig
{
	@Bean
	public MenuCashe getMenuCashe()
	{
		return MenuCashe.getInstance();
	}
	
	@Bean
	public JPAQueryFactory jpaQueryFactory(EntityManager entityManager) {
	    return new JPAQueryFactory(new HQLTemplates(), entityManager);
	}
}
