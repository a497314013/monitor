/*
 * @(#) RamonI18NProvider.java Sep 30, 2018 9:02:28 AM
 *
 * Copyright 2018 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.rockwell.ramon.ui.local;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.vaadin.external.org.slf4j.LoggerFactory;
import com.vaadin.flow.i18n.I18NProvider;

/**
 * Simple implementation of {@link I18NProvider}.
 * <p>
 * 
 * @author Bruce xiaolong Liu, Sep 30, 2018 9:07:13 AM
 */
@Component
public class TranslationProvider implements I18NProvider
{
	private static TranslationProvider instance = null;

	public static final String BUNDLE_PREFIX = "AppLang";

	public final Locale localChina = new Locale("zh", "CN");

	public final Locale localEnglish = new Locale("en", "US");

	public static TranslationProvider getInstance()
	{
		if (instance == null)
		{
			TranslationProvider instance = new TranslationProvider();
		}
		return instance;
	}

	private TranslationProvider()
	{

	}

	private List<Locale> locales = Collections.unmodifiableList(
		Arrays.asList(
			localChina,
			localEnglish));

	private static final LoadingCache<Locale, ResourceBundle> bundleCache = CacheBuilder
		.newBuilder().expireAfterWrite(
			1,
			TimeUnit.DAYS)
		.build(
			new CacheLoader<Locale, ResourceBundle>()
			{

				@Override
				public ResourceBundle load(final Locale key)
					throws Exception
				{
					return initializeBundle(
						key);
				}
			});

	@Override
	public List<Locale> getProvidedLocales()
	{
		return locales;
	}

	@Override
	public String getTranslation(String key, Locale locale, Object... params)
	{
		if (key == null)
		{
			LoggerFactory.getLogger(
				TranslationProvider.class.getName()).warn(
					"Got lang request for key with null value!");
			return "";
		}

		ResourceBundle bundle = bundleCache.getUnchecked(
			locale);

		if (bundle == null)
		{
			bundle = bundleCache.getUnchecked(
				localChina);
		}

		String value;
		try
		{
			value = bundle.getString(
				key);
		}
		catch (final MissingResourceException e)
		{
			LoggerFactory.getLogger(
				TranslationProvider.class.getName()).warn(
					"Missing resource",
					e);
			return "!" + locale.getLanguage() + ": " + key;
		}
		if (params.length > 0)
		{
			value = MessageFormat.format(
				value,
				params);
		}
		return value;
	}

	private static ResourceBundle initializeBundle(final Locale locale)
	{
		return readProperties(
			locale);
	}

	protected static ResourceBundle readProperties(final Locale locale)
	{
		final ClassLoader cl = TranslationProvider.class.getClassLoader();

		ResourceBundle propertiesBundle = null;
		try
		{
			propertiesBundle = ResourceBundle.getBundle(
				BUNDLE_PREFIX,
				locale,
				cl);
		}
		catch (final MissingResourceException e)
		{
			LoggerFactory.getLogger(
				TranslationProvider.class.getName()).warn(
					"Missing resource",
					e);
		}
		return propertiesBundle;
	}
}
