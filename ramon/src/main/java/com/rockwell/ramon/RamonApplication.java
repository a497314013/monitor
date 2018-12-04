package com.rockwell.ramon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class RamonApplication extends SpringBootServletInitializer
{
	private static final Logger log = LoggerFactory.getLogger(
		RamonApplication.class);

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
	{
		return application.sources(
			RamonApplication.class);
	}

	public static void main(String[] args)
	{
		SpringApplication.run(
			RamonApplication.class, args);
	}
}
