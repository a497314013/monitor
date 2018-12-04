package com.rockwell.ramon.backend.security;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

@Component
public class CustomSecurityFilter extends AbstractSecurityInterceptor
implements Filter
{
	@Autowired
	@Qualifier("securityMetadataSource")
    private FilterInvocationSecurityMetadataSource securityMetadataSource;

	@Autowired
	private CustomAccessDecisionManager myAccessDecisionManager;
	
    public void destroy()
    {
        // TODO Auto-generated method stub

    }

	@PostConstruct
	public void init(){
//		super.setAuthenticationManager(authenticationManager);
		super.setAccessDecisionManager(myAccessDecisionManager);
	}
	
    public void doFilter(ServletRequest arg0, ServletResponse arg1,
            FilterChain arg2) throws IOException, ServletException
    {
        FilterInvocation fileInvocation = new FilterInvocation(arg0, arg1, arg2);
        InterceptorStatusToken interceptorStatusToken = this
                .beforeInvocation(fileInvocation);
        fileInvocation.getChain().doFilter(arg0, arg1);
        this.afterInvocation(interceptorStatusToken, null);
    }

    @Override
    public Class<? extends Object> getSecureObjectClass()
    {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource()
    {
        return this.securityMetadataSource;
    }

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
}