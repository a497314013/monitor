package com.rockwell.ramon.app;

import java.time.Duration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("com.rockwell.ramon")
public class RamonApplicationProperties
{
	/**
	 * 
	 */
	private Duration delay = Duration.ofSeconds(3);
	
	/**
	 * 
	 */
	private String token;
	
}
