package com.rockwell.ramon.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.rockwell.ramon.backend.security.CustomAccessDeineHandler;
import com.rockwell.ramon.backend.security.CustomLoggedSuccessHandler;
import com.rockwell.ramon.backend.security.MD5PasswordEncoder;
import com.rockwell.ramon.backend.security.SecurityUtils;
import com.rockwell.ramon.service.CustomUserDetailsService;

/**
 * Configures spring security, doing the following:
 * <li>Bypass security checks for static resources,</li>
 * <li>Restrict access to the application, allowing only logged in users,</li>
 * <li>Set up the login form,</li>
 * <li>Configures the {@link UserDetailsServiceImpl}.</li>

 */
@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private static final String LOGIN_FAILURE_URL = "/login?error";

	@Autowired
	private CustomAccessDeineHandler accessDeineHandler;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private MD5PasswordEncoder passwordEncoder;
	
	/**
	 * The password encoder to use when encrypting passwords.
	 */
	@Bean
	public MD5PasswordEncoder passwordEncoder() {
		return new MD5PasswordEncoder();
	}
	
	/**
	 * Registers our UserDetailsService and the password encoder to be used on login attempts.
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		super.configure(auth);
		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder);
	}

	/**
	 * Require login to access internal pages and configure login form.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Not using Spring CSRF here to be able to use plain HTML for the login page
		http.csrf().disable()

				// Register our CustomRequestCache, that saves unauthorized access attempts, so
				// the user is redirected after login.
//				.requestCache().requestCache(new CustomRequestCache())

				// Restrict access to our application.
//				.and()
				.authorizeRequests()

				// Allow all flow internal requests.
				.requestMatchers(SecurityUtils::isFrameworkInternalRequest).permitAll()
//				.antMatchers("/TestView/test").permitAll()
				.anyRequest().authenticated()
//				.antMatchers("/**").authenticated()
				// Allow all requests by logged in users.
//				.anyRequest().hasAnyAuthority("User")
				// Configure the login page.
				.and().formLogin().loginPage("/login").permitAll()
				//.loginProcessingUrl(LOGIN_PROCESSING_URL)
				.failureUrl(LOGIN_FAILURE_URL)

				// Register the success handler that redirects users to the page they last tried
				// to access
				.successHandler(new CustomLoggedSuccessHandler())
				// Configure logout
				.and().logout().logoutSuccessUrl("/login")
				;
		
		//添加自定义异常入口，处理accessdeine异常
        http.exceptionHandling()
        .accessDeniedHandler(accessDeineHandler);
	}

	/**
	 * Allows access to static resources, bypassing Spring security.
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(
				// Vaadin Flow static resources
				"/VAADIN/**",

				// the standard favicon URI
				"/favicon.ico",

				// web application manifest
				"/manifest.json",
				"/sw.js",
				"/offline-page.html",

				// icons and images
				"/icons/**",
				"/images/**",

				// (development mode) static resources
				"/frontend/**",

				// (development mode) webjars
				"/webjars/**",

				// (development mode) H2 debugging console
				"/h2-console/**",

				// (production mode) static resources
				"/frontend-es5/**", "/frontend-es6/**");
	}
}
